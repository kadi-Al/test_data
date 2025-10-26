bash
pip install flask
from flask import Flask, request, redirect, url_for, render_template

app = Flask(__name__)

# Dummy database for demonstration purposes
users_db = {'user1': 'pass1', 'user2': 'pass2'}

@app.route('/login', methods=['GET'])
def login():
    return render_template('login.html')

@app.route('/do_login', methods=['POST'])
def do_login():
    username = request.form['username']
    password = request.form['password']
    
    if username in users_db and users_db[username] == password:
        return redirect(url_for('user_page'))
    else:
        return 'Invalid login', 401

@app.route('/user_page')
def user_page():
    return "Welcome to the User Page!"

if __name__ == '__main__':
    app.run(debug=True)
html
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Login</title>
  </head>
  <body>
    <h1>Login</h1>
    <form action="/do_login" method="post">
      Username: <input type="text" name="username"><br>
      Password: <input type="password" name="password"><br>
      <input type="submit" value="Login">
    </form>
  </body>
</html>
bash
pip install django
django-admin startproject mysite
cd mysite
python manage.py startapp login_app
INSTALLED_APPS = [
    ...
    'login_app',
]
from django.shortcuts import render, redirect
from django.urls import reverse

# Dummy database for demonstration purposes
users_db = {'user1': 'pass1', 'user2': 'pass2'}

def login(request):
    return render(request, 'login.html')

def do_login(request):
    if request.method == 'POST':
        username = request.POST['username']
        password = request.POST['password']
        
        if username in users_db and users_db[username] == password:
            return redirect(reverse('user_page'))
        else:
            return render(request, 'login.html', {'error': 'Invalid login'})

def user_page(request):
    return render(request, 'user_page.html')
from django.urls import path
from .views import *

urlpatterns = [
    path('login/', login, name='login'),
    path('do_login/', do_login, name='do_login'),
    path('user_page/', user_page, name='user_page'),
]
from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', include('login_app.urls')),
]
html
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Login</title>
  </head>
  <body>
    <h1>Login</h1>
    {% if error %}
        <p>{{ error }}</p>
    {% endif %}
    <form action="/do_login/" method="post">
      {% csrf_token %}
      Username: <input type="text" name="username"><br>
      Password: <input type="password" name="password"><br>
      <input type="submit" value="Login">
    </form>
  </body>
</html>