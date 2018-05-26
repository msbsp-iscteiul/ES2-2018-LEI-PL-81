import requests
from django.contrib import messages
from django.core.mail import send_mail, BadHeaderError
from django.http import HttpResponse
from django.shortcuts import render, redirect
from django.urls import reverse

from appform.decorators import requires_email
from appform.forms import ProblemInputUser, ProblemInputVariable, SendEmail, RequestEmailForm
from frontend.settings import env

BACKEND_URL = env.str('BACKEND_URL')
file_upload_url = BACKEND_URL + '/api/optimization/fileupload/'
save_configuration_url = BACKEND_URL + '/api/optimization/save/'
search_optimization_url = BACKEND_URL + '/api/optimization/searchoptimizationconfigurationbyidandemail'
execute_optimization_url = BACKEND_URL + '/api/optimization/executeoptimizationconfiguration'
configuration_history_url = BACKEND_URL + '/api/optimization/searchoptimizationconfigurationbyemail/'
search_execution_url = BACKEND_URL + '/api/optimization/searchoptimizationjobexecutionsbyemail'
download_latex_url = BACKEND_URL + '/api/optimization/searchlatexbyexecutionid/'
download_r_url = BACKEND_URL + '/api/optimization/searchrbyexecutionid/'


def welcome(request):
    """
    Welcome page
    :rtype: object
    """
    return render(request, 'welcome.html')


def request_email(request):
    """Login page that requests an email"""
    form = RequestEmailForm(request.POST or None)

    if form.is_valid():
        request.session['email'] = form.cleaned_data.get('email')
        return redirect('submission_success')
    return render(request, 'request_email.html', {'form': form})


@requires_email
def form_page1(request):
    """First page for the creation of problem configurations"""
    form = ProblemInputUser(request.POST or None, request.FILES or None, initial=request.session.get('data'))
    if form.is_valid():
        upload = request.FILES['input_jar']
        files = {
            'file': (upload.name,
                     open(upload.file.name, 'rb'))}
        data_form = {k: v for k, v in form.cleaned_data.items() if k != 'input_jar'}
        data = {'sessionId': request.session.session_key, 'data': data_form}

        p = requests.post(file_upload_url, data=data, files=files)
        info = p.json()

        request.session['data'] = info['result']
        request.session['data'].update(data_form)
        request.session['next'] = reverse('form_page2')
        messages.success(request, 'Form submitted successfully!')
        return redirect('submission_success')
    return render(request, 'form.html', {'form': form})


@requires_email
def form_page2(request):
    """Second and final page for the creation of problem configurations"""
    extra_data = {k: v for k, v in request.session.get('data').items()
                  if k in ['algorithms', 'variables', 'objectives', 'variable_type']}
    form = ProblemInputVariable(request.POST or None, request.FILES or None, **extra_data)
    info = request.session.get('data')
    if form.is_valid():
        files = None
        if request.FILES:
            upload = request.FILES['input_csv']
            files = {
                'file': (upload.name,
                         open(upload.file.name, 'rb'))}
        variables_list = []
        for i in range(info.get('variables')):
            variables_list.append(form.cleaned_data.get('variable_name_%s' % i))
        objectives_list = []
        for i in range(info.get('objectives')):
            objectives_list.append(form.cleaned_data.get('objectives_name_%s' % i))

        data_form = {k: v for k, v in form.cleaned_data.items() if k != 'input_csv'}
        data = {
            'sessionId': request.session.session_key,
            'problemName': info.get('name'),
            'email': request.session.get('email'),
            'description': info.get('description'),
            'executionMaxWaitTime': info.get('waiting_time'),
            'algorithmChoiceMethod': data_form.get('algorithm_choice_method'),
            'variables': variables_list,
            'objectives': objectives_list,
            'algorithms': data_form.get('choices')
        }
        p = requests.post(save_configuration_url, data=data, files=files)
        info = p.json()
        if 'result' not in info:
            messages.error(request, info['message'], 'danger')
        elif info['result']['message']:
            messages.error(request, info['result']['message'], 'danger')
        else:
            del request.session['data']
            request.session['next'] = reverse('request_details', args=[info['result']['id']])
            messages.success(request, 'Your configuration was submitted successfully!')
            return redirect('submission_success')

    return render(request, 'form2.html', {
        'form': form, 'variables': extra_data['variables'],
        'objectives': extra_data['objectives'],
    })


@requires_email
def configuration_details(request, num):
    """
    Problem configuration details page controller
    :type num: int
    """
    if request.POST:
        if submit_execution_request(num, request.session.get('email')):
            request.session['next'] = reverse('history')
            messages.success(request, 'Execution request sent successfully!')
            return redirect('submission_success')
        else:
            messages.error(request, 'Couldn\'t submit execution request. Please try later')
    request.session['idSubmission'] = num
    data = {'id': num, 'email': request.session.get('email')}
    p = requests.post(search_optimization_url, data=data)
    info = p.json()
    jar = info['result']['optimizationConfiguration']['filePath'].split('/')[-1]
    info['result']['optimizationConfiguration']['filePath'] = jar
    list_algo = []
    for var in info['result']['optimizationConfiguration']['algorithms']:
        list_algo.append(var['name'].split('.')[-1])
    # data = info['result']['optimizationConfiguration']
    info['result']['optimizationConfiguration']['algorithms'] = list_algo
    return render(request, 'configuration_details.html', {
        'details': info['result']['optimizationConfiguration'],
    })


def submit_execution_request(num, email):
    """
    Submits a problem configuration for execution
    :type num: int
    :type email: str
    """
    data = {'id': num, 'email': email}
    result = requests.post(execute_optimization_url, data=data).json()
    return result['result']['id']


@requires_email
def processing(request):
    """Processing status page"""
    return render(request, 'processing.html')


def faq_page(request):
    """FAQ page"""
    return render(request, 'faq.html')


@requires_email
def my_configurations(request):
    """Problem configuration list page"""
    configurations = requests.get(configuration_history_url, params={'email': request.session.get('email')})\
        .json()['result']['summary']
    return render(request, 'my_configurations.html', {
        'configurations': configurations,
    })


@requires_email
def execution_history(request):
    """Execution list page"""
    executions = requests.post(search_execution_url, data={'email': request.session.get('email')})\
        .json()['result']['executions']
    return render(request, 'execution_history.html', {
        'executions': executions
    })


@requires_email
def execution_details(request, optimization_configuration_id, execution_id):
    """
    A problem configuration execution detail page
    :type optimization_configuration_id: int
    :type execution_id: int
    """
    configuration = requests.post(search_optimization_url, data={
        'email': request.session.get('email'),
        'id': optimization_configuration_id
    }).json()['result']['optimizationConfiguration']
    execution = next(obj for obj in configuration['executions'] if obj['id'] == execution_id)
    solutions = sorted(execution['solutions'], key=lambda solution: solution['quality'])
    objectives = [obj['name'] for obj in configuration['objectives']]

    return render(request, 'details.html', {
        'configuration': configuration,
        'execution': execution,
        'objectives': objectives,
        'solutions': solutions
    })


@requires_email
def download_latex(request, execution_id):
    """
    Download latex file
    :type execution_id: int
    """
    file = requests.post(download_latex_url, data={'id': execution_id})
    response = HttpResponse(file, 'application/pdf')
    response['Content-Disposition'] = 'attachment; filename="latex.pdf"'
    return response


@requires_email
def download_r(request, execution_id):
    """
    Download R file
    :type execution_id: int
    """
    file = requests.post(download_r_url, data={'id': execution_id})
    response = HttpResponse(file, 'application/postscript')
    response['Content-Disposition'] = 'attachment; filename="r.eps"'
    return response


@requires_email
def send_email(request):
    """Send support request email"""
    email_request = SendEmail(request.POST or None)
    if email_request.is_valid():
        subject = email_request.cleaned_data['subject']
        message = email_request.cleaned_data['message']
        from_email = request.session.get('email')
        if subject and message and from_email:
            try:
                send_mail(subject, message, env.str('EMAIL_ADMIN'), [env.str('EMAIL_ADMIN')])
                request.session['next'] = request.path
                messages.success(request, 'Message sent successfully!')
                return redirect('submission_success')
            except BadHeaderError:
                return HttpResponse('Invalid header found.')
    return render(request, 'contacts.html', {'email_request': email_request})


def submission_success(request):
    """Generic success page that redirects to a page set in a session variable"""
    return render(request, 'success.html', {
        'next': request.session.pop('next')
    })


@requires_email
def logout(request):
    """Logout page that removes the email inserted by the client"""
    del(request.session['email'])
    request.session['next'] = reverse('welcome')
    messages.success(request, 'Logged out successfully!')
    return redirect('submission_success')
