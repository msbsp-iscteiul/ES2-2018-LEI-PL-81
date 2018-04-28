from django import forms


# Formulário que trata o pedido de email, caso não exista em sessão
class RequestEmailForm(forms.Form):
    email = forms.EmailField(label='Email',
                             help_text='Please, enter your email -- Acrescentar algo')

    def clean(self):
        cleaned_data = super(RequestEmailForm, self).clean()
        email = cleaned_data.get('email')
        if not email:
            self.add_error('email', 'Please, enter your email')


class ProblemInputUser(forms.Form):
    name = forms.CharField(
        max_length=30,
        label='Problem name',
        help_text='Write the problem name using Java Class Name Convention')
    description = forms.CharField(
        label='Description',
        max_length=500,
        widget=forms.Textarea(attrs={
            'rows': 3
        }),
        help_text='Write the description of the problem to solve')
    waiting_time = forms.IntegerField(
        help_text='Write the maximum time you are willing to wait for optimization of the problem')
    input_jar = forms.FileField(label='Select a file')

    def clean(self):
        cleaned_data = super(ProblemInputUser, self).clean()
        name = cleaned_data.get('name')
        description = cleaned_data.get('description')
        waiting_time = cleaned_data.get('max')
        input_jar = cleaned_data.get('input_jar')
        if not (name or description or waiting_time or input_jar):
            raise forms.ValidationError('You have to fill out the form!')


class ProblemInputVariable(forms.Form):

    def __init__(self, *args, **kwargs):
        algorithms = kwargs.pop('algorithms')
        variables = kwargs.pop('variables')
        objectives = kwargs.pop('objectives')
        variable_type = kwargs.pop('variable_type')
        super(ProblemInputVariable, self).__init__(*args, **kwargs)
        self.fields['variables'] = forms.IntegerField(
            label='Number of variables',
            widget=forms.TextInput(attrs={'readonly': True}))
        self.fields['variable_type'] = forms.Field(
            label='Solution type',
            widget=forms.TextInput(attrs={'readonly': True}))
        for i in range(variables):
            self.fields['variable_name_%s' % i] = forms.Field(label='Variable Name %s' % (i + 1), required=False)
        self.fields['objectives'] = forms.IntegerField(
            label='Number of objectives',
            widget=forms.TextInput(attrs={'readonly': True}))
        for i in range(objectives):
            self.fields['objectives_name_%s' % i] = forms.Field(label='Objectives Name %s' % (i + 1), required=False)
        self.fields['variables'].initial = variables
        self.fields['variable_type'].initial = variable_type
        self.fields['objectives'].initial = objectives
        self.fields['input_csv'] = forms.FileField(label='Select the csv with the best solution you have')
        self.fields['algorithm_choice_method'] = forms.ChoiceField(
            label='Select the algorithm choice method',
            choices=(
                ('Manual', 'Manual'),
                ('Automatic', 'Automatic'),
                ('Mixed', 'Mixed')
            )
        )
        self.fields['choices'] = forms.MultipleChoiceField(
            label='Select the algorithms to run the problem with',
            choices=zip(algorithms, [algorithm.split('.')[-1] for algorithm in algorithms]),
            widget=forms.CheckboxSelectMultiple,
            required=True
        )

        def clean(self):
            cleaned_data = super(ProblemInputVariable, self).clean()
            variables = cleaned_data.get('variables')
            variable_type = cleaned_data.get('variable_type')
            objectives = cleaned_data.get('objectives')
            input_csv = cleaned_data.get('input_csv')
            algorithms = cleaned_data.get('algorithms') or []
            if len(algorithms) > 3 or len(algorithms) == 0:
                raise forms.ValidationError("You have to select between 0 and 3 items.")
            for i in range(variables):
                if cleaned_data['variable_name_%s' % i] == '':
                    self.cleaned_data['variable_name_%s' % i] = 'var%s' % (i + 1)
            for i in range(objectives):
                if cleaned_data['objectives_name_%s' % i] == '':
                    self.cleaned_data['objectives_name_%s' % i] = 'obj%s' % (i + 1)
            if not (objectives or variables or variable_type or input_csv or algorithms):
                raise forms.ValidationError('You have to fill out the form!')


class SendEmail(forms.Form):
    subject = forms.CharField(
        max_length=30,
        label='Subject')
    message = forms.CharField(
        label='Message',
        max_length=500,
        widget=forms.Textarea(),
        help_text='Tell us about your questions')
