import requests
from appform.forms import ProblemInputUser, ProblemInputVariable, SendEmail, RequestEmailForm
from django.core.mail import send_mail, BadHeaderError
from django.http import HttpResponse
from django.shortcuts import render, redirect
from appform.decorators import enter_email
from frontend.settings import BACKEND_URL

file_upload_url = BACKEND_URL + '/api/optimization/fileupload/'
save_configuration_url = BACKEND_URL + '/api/optimization/save/'
search_optimization_url = BACKEND_URL + '/api/optimization/searchoptimizationconfigurationbyidandemail'
execute_optimization_url = BACKEND_URL + '/api/optimization/executeoptimizationconfiguration'


def welcome(request):
    return render(request, 'welcome.html')


def request_email(request):
    form = RequestEmailForm(request.POST or None)

    if form.is_valid():
        request.session['email'] = form.cleaned_data.get('email')
        return redirect('welcome')
    return render(request, 'request_email.html', {'form': form})


@enter_email
def form_page1(request):
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
        return redirect('/form2')
    return render(request, 'form.html', {'form': form})


@enter_email
def form_page2(request):
    extra_data = {k: v for k, v in request.session.get('data').items()
                  if k in ['algorithms', 'variables', 'objectives', 'variable_type']}
    form = ProblemInputVariable(request.POST or None, request.FILES or None, **extra_data)
    info = request.session.get('data')
    error = None
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
        if info['result']['id'] is None:
            error = info['result']['message']
        else:
            return redirect('request_details', info['result']['id'])

    return render(request, 'form2.html', {
        'form': form, 'variables': extra_data['variables'],
        'objectives': extra_data['objectives'],
        'error': error
    })


@enter_email
def request_details(request, num):
    error = None
    if request.POST:
        error = submit_execution_request(num, request.session.get('email'))
        if error is None:
            redirect('processing')
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
        'error': error
    })


def submit_execution_request(num, email):
    data = {'id': num, 'email': email}
    result = requests.post(execute_optimization_url, data=data).json()
    if result['result']['id'] is None:
        return 'Couldn\'t submit execution request. Please try later'


def processing(request):
    return render(request, 'processing.html')


def faq_page(request):
    return render(request, 'faq.html')


@enter_email
def my_configurations(request):
    email_conf = request.session.get('email')
    return render(request, 'my_configurations.html', {'user_email': email_conf})


@enter_email
def execution_history(request):
    email_conf = request.session.get('email')
    return render(request, 'execution_history.html', {'user_email': email_conf})


@enter_email
def configuration_detail(request, num):
    return render(request, 'details.html')


@enter_email
def send_email(request):
    if request.method == 'POST':
        email_request = SendEmail(request.POST)
        if email_request.is_valid():
            subject = email_request.cleaned_data['subject']
            message = email_request.cleaned_data['message']
            from_email = request.session.get('email')
            if subject and message and from_email:
                try:
                    send_mail(subject, message, from_email, ['mj-17.94@hotmail.com'])
                except BadHeaderError:
                    return HttpResponse('Invalid header found.')

    else:
        email_request = SendEmail()
    return render(request, 'contacts.html', {'email_request': email_request})
