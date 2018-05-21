import requests
from appform.forms import ProblemInputUser, ProblemInputVariable, SendEmail, RequestEmailForm
from django.core.mail import send_mail, BadHeaderError
from django.http import HttpResponse
from django.shortcuts import render, redirect
from appform.decorators import enter_email

url_upload = 'http://172.17.10.175:8080/api/optimization/fileupload/'
url_upload2 = 'http://172.17.10.175:8080/api/optimization/save/'
url_upload3 = 'http://172.17.10.175:8080/api/optimization/searchoptimizationconfigurationbyidandemail'
url_upload4 = 'http://172.17.10.175:8080/api/optimization/executeoptimizationconfiguration'


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
    form = ProblemInputUser(request.POST or None, request.FILES or None)
    error = None
    if form.is_valid():
        upload = request.FILES['input_jar']
        files = {
            'file': (upload.name,
                     open(upload.file.name, 'rb'))}
        data_form = {k: v for k, v in form.cleaned_data.items() if k != 'input_jar'}
        data = {'sessionId': request.session.session_key, 'data': data_form}

        p = requests.post(url_upload, data=data, files=files)
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
        variablesList = []
        objectivesList = []
        for i in range(info.get('variables')):
            variablesList.append(form.cleaned_data.get('variable_name_%s' % i))
        for i in range(info.get('objectives')):
            objectivesList.append(form.cleaned_data.get('objectives_name_%s' % i))

        data_form = {k: v for k, v in form.cleaned_data.items() if k != 'input_csv'}
        data = {'sessionId': request.session.session_key, 'problemName': info.get('name'),
                'email': request.session.get('email'), 'description': info.get('description'),
                'executionMaxWaitTime': info.get('waiting_time'),
                'algorithmChoiceMethod': data_form.get('algorithm_choice_method'),
                'variables': variablesList, 'objectives': objectivesList, 'algorithms': data_form.get('choices')
                }
        p = requests.post(url_upload2, data=data, files=files)
        info = p.json()
        if info['result']['id'] == None:
            error = info['result']['message']
        else:
            return redirect('/requestdetails/' + str(info['result']['id']))

    return render(request, 'form2.html', {
        'form': form, 'variables': extra_data['variables'],
        'objectives': extra_data['objectives'],
        'error': error
    })


@enter_email
def request_details(request, num):
    if request.POST:
        return redirect('/processing/' + str(num))
    request.session['idSubmission'] = num
    data = {'id': num, 'email': request.session.get('email')}
    p = requests.post(url_upload3, data=data)
    info = p.json()
    jar = info['result']['optimizationConfiguration']['filePath'].split('/')[-1]
    info['result']['optimizationConfiguration']['filePath'] = jar
    listAlgo = []
    for var in info['result']['optimizationConfiguration']['algorithms']:
        listAlgo.append(var['name'].split('.')[-1])
    # data = info['result']['optimizationConfiguration']
    info['result']['optimizationConfiguration']['algorithms'] = listAlgo
    return render(request, 'request_details.html', {'details': info['result']['optimizationConfiguration']})


def submit_problem(request, num):
    data = {'id': num, 'email': request.session.get('email')}
    # p vem com id
    p = requests.post(url_upload4, data=data)
    return render(request, 'submit_page.html', {'num': num})


def faq_page(request):
    return render(request, 'faq.html')


@enter_email
def saved_conf(request):
    email_conf = request.session.get('email')
    return render(request, 'save_conf.html', {'user_email': email_conf})


@enter_email
def history(request):
    email_conf = request.session.get('email')
    return render(request, 'history.html', {'user_email': email_conf})


@enter_email
def details(request, num):
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
