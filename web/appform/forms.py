from django import forms
from appform import choices


class ProblemInputUser(forms.Form):
    name = forms.CharField(
        max_length=30,
        label='Name')
    description = forms.CharField(
        label='Description',
        max_length=500,
        widget=forms.Textarea(),
        help_text='Write the description of the problem to solve')
    email = forms.EmailField(
        max_length=254,
        label='Email')
    max_time = forms.IntegerField(
        help_text='Write the maximum time you are willing to wait for optimization of the problem')
    input_jar = forms.FileField(label='Select a file')

    def clean(self):
        cleaned_data = super(ProblemInputUser, self).clean()
        name = cleaned_data.get('name')
        description = cleaned_data.get('description')
        email = cleaned_data.get('email')
        max_time = cleaned_data.get('max')
        input_jar = cleaned_data.get('input_jar')
        if not (name or description or email or max_time or input_jar):
            raise forms.ValidationError('You have to fill out the form!')


class ProblemInputVariable(forms.Form):
    num_var = forms.IntegerField(
        label='Variables',
        help_text='Number of variables to analyze')
    num_obj = forms.IntegerField(
        label='Objectives',
        help_text='')
    varName = forms.CharField(
        max_length=30,
        label='Variable name')
    varType = forms.ChoiceField(
        choices=choices.type_choices,
        required=True)
    var_min = forms.Field(label='Variable minimum limit')
    var_max = forms.Field(label='Variable maximum limit')
    var_inv = forms.Field(label='Variable invalid value')
    objName = forms.CharField(max_length=30,
                              label='Objective name')


class SendEmail(forms.Form):
    email_client = forms.EmailField(
        max_length=254,
        label='Email')
    subject = forms.CharField(
        max_length=30,
        label='Subject')
    message = forms.CharField(
        label='Message',
        max_length=500,
        widget=forms.Textarea(),
        help_text='Tell us about your questions')
