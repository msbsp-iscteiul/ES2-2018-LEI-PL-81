from appform.forms import ProblemInputUser, ProblemInputVariable, SendEmail
from django.core.mail import send_mail, BadHeaderError
from django.http import HttpResponse
from django.shortcuts import render, redirect
import requests


def welcome(request):
    return render(request, 'welcome.html')


def init_form(request):
    if request.method == 'POST':
        form = ProblemInputUser(request.POST, request.FILES)
    #    url_upload = 'http://172.17.12.70:8080/api/upload/'
        if form.is_valid():
            upload = request.FILES['input_jar']
            request.session['form'] = {k: v for k, v in form.cleaned_data.items() if k != 'input_jar'}

            request.session['file'] = upload.name
            # print(request.session.session_key)
            files = {
                'file': (upload.name,
                         open(upload.file.name, 'rb'))}
            data = {'sessionId': request.session.session_key}
    #       p = requests.post(url_upload, data=data, files=files)
    #        print(p.text)
            # key = request.session._session_key
            exp = {
                'number_variables': 3,
                'varType': 'DoubleSolution'
            }
            request.session['data'] = exp
            return redirect('/form2')
    else:
        form = ProblemInputUser()
    return render(request, 'form.html', {'form': form})


def form_page2(request):

    if request.method == 'POST':
        post = request.POST.dict()
        form = ProblemInputVariable(data=post)

        if form.is_valid():
            request.session['form2'] = {k: v for k, v in form.cleaned_data.items()}
            d = request.session.get('form2')
            print(d)
            return redirect('processing/')
    else:
        data_session = request.session.get('data')
        form = ProblemInputVariable(data=data_session)
    return render(request, 'form.html', {'form': form})


def submit_problem(request):
    return 'We are preocessing you problem! Thank you!'


def saved_conf(request):
    return render(request, 'save_conf.html')


def faq_page(request):
    return render(request, 'faq.html')


def history(request):
    return render(request, 'history.html')


def send_email(request):
    if request.method == 'POST':
        email_request = SendEmail(request.POST)
        if email_request.is_valid():
            subject = email_request.cleaned_data['subject']
            message = email_request.cleaned_data['message']
            from_email = email_request.cleaned_data['email_client']
        if subject and message and from_email:
            try:
                send_mail(subject, message, from_email, ['mj-17.94@hotmail.com'])
            except BadHeaderError:
                return HttpResponse('Invalid header found.')

    else:
        email_request = SendEmail()
    return render(request, 'contacts.html', {'email_request': email_request})
