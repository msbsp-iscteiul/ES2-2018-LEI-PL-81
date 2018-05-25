import os
from django.core.exceptions import ValidationError


def validate_file_ext(ext_list):
    """
    Creates a file extension validator for the given list of extensions
    :type ext_list: list
    """
    def validate_file_extension(value):
        """
        Validates a file for the configured list of extensions
        :type value: file
        """
        ext = os.path.splitext(value.name)[1]  # [0] returns path+filename
        if not ext.lower() in ext_list:
            raise ValidationError(u'Unsupported file extension.')

    return validate_file_extension
