from django.urls import path

from . import views

urlpatterns = [
    path('', views.welcome, name='welcome'),
    path('login/', views.request_email, name='request_email'),
    path('optimization_configuration_1/', views.form_page1, name='form_page1'),
    path('optimization_configuration_2/', views.form_page2, name='form_page2'),
    path('configuration_details/<int:num>', views.configuration_details, name='request_details'),
    path('processing', views.processing, name='processing'),
    path('faq/', views.faq_page, name='faq_page'),
    path('execution_history/', views.execution_history, name='history'),
    path('configurations/', views.my_configurations, name='saved_conf'),
    path('contacts/', views.send_email, name='send_email'),
    path('history/details/<int:num>', views.configuration_detail, name='history_details'),
    path('submission_success', views.submission_success, name='submission_success'),
    path('logout', views.logout, name='logout'),
]
