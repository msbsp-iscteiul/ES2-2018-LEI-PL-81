from django.shortcuts import render, redirect

from appform.forms import ProblemInputUser, ProblemInputVariable


def welcome(request):
    return render(request, 'welcome.html')


def init_form(request):

    if request.method == 'POST':
        form = ProblemInputUser(request.POST)
        if form.is_valid():
            for var in form:
                print(var.value())
            return redirect(form_page2)
        pass  # does nothing, just trigger the validation

    else:
        form = ProblemInputUser()
    return render(request, 'form.html', {'form': form})


def form_page2(request):
    if request.method == 'POST':
        form = ProblemInputVariable(request.POST)
        if form.is_valid():
            for var in form:
                print(var.value())
            return redirect(form_page2)
        pass  # does nothing, just trigger the validation

    else:
        form = ProblemInputVariable()
    return render(request, 'form.html', {'form': form})


def faq_page(request):
    return ()

