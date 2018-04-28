from functools import wraps
from django.shortcuts import redirect


def enter_email(view_func):

    @wraps(view_func)
    def _wrapped_view(request, *args, **kwargs):
        if request.session.get('email') is None:
            return redirect('request_email')
        return view_func(request, *args, **kwargs)

    return _wrapped_view
