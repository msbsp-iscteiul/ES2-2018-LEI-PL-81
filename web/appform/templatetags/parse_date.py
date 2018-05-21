from django import template
from dateutil.parser import parse

register = template.Library()


@register.filter(expects_localtime=True)
def parse_date(value):
    return value and parse(value) or ''
