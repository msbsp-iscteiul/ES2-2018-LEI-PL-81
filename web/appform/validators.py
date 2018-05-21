import os
from django.core.exceptions import ValidationError


def validate_file_ext(ext_list):

    def validate_file_extension(value):
        ext = os.path.splitext(value.name)[1]  # [0] returns path+filename
        if not ext.lower() in ext_list:
            raise ValidationError(u'Unsupported file extension.')

    return validate_file_extension
