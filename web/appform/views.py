from django.http import HttpResponseRedirect, HttpResponse
from django.shortcuts import render, redirect
from appform.forms import ProblemInputUser, ProblemInputVariable, SendEmail
import requests
from django.core.mail import send_mail, BadHeaderError


def welcome(request):
    return render(request, 'welcome.html')


def init_form(request):
    if request.method == 'POST':
        form = ProblemInputUser(request.POST, request.FILES)
        url = 'https://requestb.in/u5b837u5'

        if form.is_valid():
            upload = request.FILES['input_jar']
            request.session['form'] = {k: v for k, v in form.cleaned_data.items() if k != 'input_jar'}
            request.session['file'] = upload.name
            print(request.session.session_key)

            files = {
                'file': (upload.name,
                         open(upload.file.name, 'rb'))}
            r = requests.post(url, files=files)
            s = request.session._session_key
            print(r.text)

            return HttpResponseRedirect('/form2')
    else:
        form = ProblemInputUser()
    return render(request, 'form.html', {'form': form})


def form_page2(request):
    print(request.session.session_key)
    print(request.session['form'])
    print(request.session['file'])
    if request.method == 'POST':
        form = ProblemInputVariable(request.POST)
        if form.is_valid():
            return redirect(form_page2)
        pass  # does nothing, just trigger the validation

    else:
        form = ProblemInputVariable()
    return render(request, 'form.html', {'form': form})


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
