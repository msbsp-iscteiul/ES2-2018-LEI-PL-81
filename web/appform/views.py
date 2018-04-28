import requests
from appform.forms import ProblemInputUser, ProblemInputVariable, SendEmail, RequestEmailForm
from django.core.mail import send_mail, BadHeaderError
from django.http import HttpResponse
from django.shortcuts import render, redirect
from appform.decorators import enter_email

url_upload = 'http://172.17.9.217:8080/api/optimization/fileupload/'


@enter_email
def welcome(request):
    return render(request, 'welcome.html')


def request_email(request):
    form = RequestEmailForm(request.POST or None)

    if form.is_valid():
        request.session['email'] = form.cleaned_data.get('email')
        return redirect('welcome')
    return render(request, 'request_email.html', {'form': form})


@enter_email
def init_form(request):
    if request.method == 'POST':
        form = ProblemInputUser(request.POST, request.FILES)
        #    url_upload = 'http://172.17.12.70:8080/api/upload/'
        if form.is_valid():
            upload = request.FILES['input_jar']
            # print(request.session.session_key)
            files = {
                'file': (upload.name,
                         open(upload.file.name, 'rb'))}
            data_form = {k: v for k, v in form.cleaned_data.items() if k != 'input_jar'}
            data = {'sessionId': request.session.session_key, 'data': data_form}

            # Confirmar como vem a resposta! se preciso de uma get ou "p = " basta
            p = requests.post(url_upload, data=data, files=files)
            info = p.json()
            # info = {'result': {'objectives': 2, 'variables': 10, 'variable_type': 'Double',
            #                    'algorithms': ['a', 'b', 'c', 'd']},
            #         'SessionID': 1203883}
            # info['result']['algor_selection'] = data_form.get('algor_selection')
            request.session['data'] = info['result']
            request.session['data'].update(data_form)
            return redirect('/form2')
    else:
        form = ProblemInputUser()
    return render(request, 'form.html', {'form': form})


@enter_email
def form_page2(request):
    extra_data = request.session.get('data')
    form = ProblemInputVariable(request.POST or None, request.FILES or None, **extra_data)

    if form.is_valid():
        upload = request.FILES['input_csv']
        files = {
            'csv_file': (upload.name,
                         open(upload.file.name, 'rb'))}

        data_form = {k: v for k, v in form.cleaned_data.items() if k != 'input_csv'}
        data = {'sessionId': request.session.session_key, 'data': data_form}
        # requests.post(url_upload, data=data, files=files)

        return redirect('/processing')

    return render(request, 'form2.html', {
        'form': form, 'variables': extra_data['variables'],
        'objectives': extra_data['objectives'],
    })


def submit_problem(request):
    return render(request, 'submit_page.html')


@enter_email
def saved_conf(request):
    return render(request, 'save_conf.html')


def faq_page(request):
    return render(request, 'faq.html')


def history(request):
    email_conf = request.session.get('email')
    conf = requests.get(url_upload, email_conf)
    return render(request, 'history.html', {'conf': conf})


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
