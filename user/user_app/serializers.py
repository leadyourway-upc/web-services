from rest_framework import serializers
from .models import CustomUser  # Import your user model here

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = CustomUser  # Replace with your user model
        exclude = ['groups', 'user_permissions']
