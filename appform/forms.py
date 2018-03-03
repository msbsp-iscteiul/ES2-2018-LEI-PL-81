from django import forms
from appform import choices


class ProblemInputUser(forms.Form):
    name = forms.CharField(max_length=30,
                           label='Name')
    description = forms.CharField(
        label='Description',
        max_length=500,
        widget=forms.Textarea(),
        help_text='Write the description of the problem to solve')
    email = forms.EmailField(max_length=254,
                             label='Email')
    max = forms.IntegerField(
        help_text='Write the maximum time you are willing to wait for optimization of the problem')
    num_var = forms.IntegerField(label='Variables',
                                 help_text='Number of variables to analyze')
    num_obj = forms.IntegerField(label='Objectives',
                                 help_text='')


class ProblemInputVariable(forms.Form):
    varName = forms.CharField(max_length=30,
                              label='Variable name')
    varType = forms.ChoiceField(choices=choices.type_choices, required=True)
    var_min = forms.Field(label='Variable minimum limit')
    var_max = forms.Field(label='Variable maximum limit')
    var_inv = forms.Field(label='Variable invalid value')
    objName = forms.CharField(max_length=30,
                              label='Objective name')
    jar = forms.FileInput()




# def clean(self):
#     cleaned_data = super(ProblemInput, self).clean()
#     print(cleaned_data)
#     name = cleaned_data.get('name')
#     description = cleaned_data.get('description')
#     email = cleaned_data.get('email')
#     print(email)
#     max = cleaned_data.get('max')
#     if not email:
#         raise forms.ValidationError('You have to fill out the form!')
#
