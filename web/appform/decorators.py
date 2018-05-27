from functools import wraps
from django.shortcuts import redirect


def requires_email(view_func):
    """View decorator to ensure the user is logged in"""
    @wraps(view_func)
    def _wrapped_view(request, *args, **kwargs):
        """Returns the wrapped view for execution by Django"""
        if not request.session.get('email'):
            request.session['next'] = request.path
            return redirect('request_email')
        return view_func(request, *args, **kwargs)

    return _wrapped_view
