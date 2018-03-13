from django.urls import path

from . import views

urlpatterns = [
    path('', views.welcome, name='welcome'),
    path('form/', views.init_form, name='init_form'),
    path('form2/', views.form_page2, name='form_page2'),
    path('faq/', views.faq_page, name='faq_page'),
    path('history', views.history, name='history'),
    path('configurations', views.saved_conf, name='saved_conf'),
    path('contacts', views.send_email, name='send_email')
]
