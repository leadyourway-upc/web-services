"""
URL configuration for user project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from user_app import views

from drf_yasg.views import get_schema_view
from drf_yasg import openapi

schema_view = get_schema_view(
    openapi.Info(
        title="Your API Title",
        default_version='v1',
        description="Your API Description",
        contact=openapi.Contact(email="your@email.com"),
    ),
    public=True,
)

urlpatterns = [
    path('swagger/', schema_view.with_ui('swagger', cache_timeout=0), name='schema-swagger-ui'),
    path('admin/', admin.site.urls),
    path('api/users/', views.UserCreateView.as_view(), name='user-create'),
    path('api/users/<int:pk>/', views.UserUpdateView.as_view(), name='user-update'),
    path('api/users/<int:pk>/delete/', views.UserDeleteView.as_view(), name='user-delete'),
    path('api/users/<int:pk>/retrieve/', views.UserRetrieveView.as_view(), name='user-retrieve'),
    path('api/users/list/', views.UserListView.as_view(), name='user-list'),
]
