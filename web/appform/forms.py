from django import forms


class ProblemInputUser(forms.Form):
    name = forms.CharField(
        max_length=30,
        label='Problem name',
        help_text='Write the problem name using Java Class Name Convention')
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

    def __init__(self, *args, **kwargs):
        data = kwargs.pop('data')
        print('data __init__ form')
        print(data)
        number_variables = data.get('number_variables')
        super(ProblemInputVariable, self).__init__(data, *args, **kwargs)
        count = 0
        while count < number_variables:
            self.fields['custom_%s' % count] = forms.Field(label='Variable Name %s' % count)
            count = count + 1

    num_obj = forms.IntegerField(label='Objectives')
    number_variables = forms.IntegerField(label='Number of variables')
    varType = forms.Field(required=True)
    var_min = forms.IntegerField(label='Variable minimum limit')
    var_max = forms.IntegerField(label='Variable maximum limit')
    var_inv = forms.IntegerField(label='Variable invalid value')
    objName = forms.CharField(max_length=30, label='Objective name')

    def clean(self):
        cleaned_data = super(ProblemInputVariable, self).clean()
        num_obj = cleaned_data.get('num_obj')
        number_variables = cleaned_data.get('number_variables')
        varType = cleaned_data.get('varType')
        var_min = cleaned_data.get('var_min')
        var_max = cleaned_data.get('var_max')
        objName = cleaned_data.get('objName')
        if not (num_obj or number_variables or varType or var_min or var_max or objName):
            raise forms.ValidationError('You have to fill out the form!')


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
