from django.urls import path

from . import views

urlpatterns = [
    path('', views.welcome, name='welcome'),
    path('login/', views.request_email, name='request_email'),
    path('form/', views.form_page1, name='form_page1'),
    path('form2/', views.form_page2, name='form_page2'),
    path('requestdetails/<int:num>', views.request_details, name='request_details'),
    path('processing/<int:num>', views.submit_problem, name='submit_problem'),
    path('faq/', views.faq_page, name='faq_page'),
    path('history/', views.history, name='history'),
    path('configurations/', views.saved_conf, name='saved_conf'),
    path('contacts/', views.send_email, name='send_email'),
    path('history/details/<int:num>', views.details, name='history_details')
]
