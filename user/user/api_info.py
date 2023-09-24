from django.utils.translation import ugettext_lazy as _
from rest_framework import exceptions

api_info = {
    'info': {
        'title': 'Your API Title',
        'version': 'v1',
        'description': 'Your API Description',
        'contact': {
            'email': 'your@email.com',
        },
    },
    'externalDocs': {
        'description': _('Find more info here'),
        'url': 'https://www.example.com/docs',
    },
}

