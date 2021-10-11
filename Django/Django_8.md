## 로그인, 로그아웃 하기

로그인과 로그아웃 기능은 CRUD나 회원가입보다 훨씬 간단한 것 같다. 모델을 건드릴 필요가 없어서 함수작성, url path연결, 템플릿 작성만 하면 되기 때문에..!

본격적으로 시작해보자 



**1. account앱을 만들고, settings.py에 앱을 만들었다고 알려준다.**



**2. urls.py를 만들고, settings.py가 있는 폴더의 urls.py에 연결해준다.** 

```python
from django.contrib import admin
from django.urls import path, include
from apply.views import home

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', home, name="urlnamehome"),
    path('apply/',include('apply.urls')),
    path('account/',include('account.urls')),
]

```



**3. views.py에 가서 login_view와 logout_view 라는 이름의 함수를 작성한다.**

```python
from django.http import request
from account.models import CustomUser
from django.shortcuts import redirect, render
from django.contrib.auth.forms import AuthenticationForm
from django.contrib.auth import authenticate, login, logout
from django.contrib import messages
# Create your views here.

def login_view(request):
    if request.method == "POST":
        auth_form = AuthenticationForm(request=request,data=request.POST)
        if auth_form.is_valid():
            v_username = auth_form.cleaned_data.get('username')
            v_password = auth_form.cleaned_data.get('password')
            auth_user = authenticate(request=request, username = v_username, password = v_password)
            login(request, auth_user)
            return redirect('urlnamehome')
        else:
            if CustomUser.objects.filter(username=request.POST['username']).exists():
                messages.info(request,'비밀번호가 일치하지 않습니다.')
                return redirect('urlnamelogin')
            else: 
                messages.info(request,'존재하지 않는 계정입니다.')
                return redirect('urlnamelogin')
    else:   
        login_form = AuthenticationForm()
        return render(request,'login.html',{'views_login_form':login_form})
```

로그인 함수에서는 사용자 인증을 위한 AuthenticationForm을 사용한다. 위의 코드의 else부분에는 CustomUser라는 모델이 등장하는데, 이는 다음에 바로 다룰 회원가입 기능 구현에서 작성하게 될 class이다. 일단은 if문을 통해 로그인 과정에서 발생할 에러들에 그에 맞는 메시지를 띄우기 위해 해당 모델이 잠시 등장했다. 



```python
def logout_view(request):
    logout(request)
    return redirect('urlnamehome')
```

위는 로그아웃 함수이다. django에 있는 기능을 가져다가 쓰기 때문에 매우 간단하다. 





**4. urls.py에서 path 연결해주기**

```python
from django.urls import path
from account.views import *

urlpatterns = [
    path('login/',login_view,name='urlnamelogin'),
    path('logout/',logout_view,name='urlnamelogout'),
]  
```



**5. login.html에 템플릿 작성하기**

```python
{% extends 'base.html' %}
{% block content %}

<div style="text-align: center;">
    {% if messages %}
    {% for m in messages %}
        <div style="text-align: center; color: red;">
            <strong>{{m.message}}</strong><br>
        </div>
    {% endfor %}
    {% endif %}
    <form action="{% url 'urlnamelogin' %}" method="POST">
    {% csrf_token %}
    {{views_login_form.as_p}}
    <button type="submit" class="btn btn-warning">로그인</button>
    </form>
</div>
{% endblock %}
```

for문으로 메시지를 돌려서 에러가 나면 메시지가 뜨도록 작성하였다. 참고로 logout은 따로 템플릿이 필요없다. 





**6. 마지막으로 홈 화면이나 navbar에 로그인과 로그아웃이 뜨도록 설정해주면 끝!**

```python
    {% if user.is_authenticated %}
    <h3>{{user.name}}님🦁 반갑습니다!</h3><br>     # 로그인한 것을 알 수 있도록 로그인 시 이름을 띄운다
    <a href="{% url 'urlnamelogout' %}">로그아웃</a>
    {% else %}
    <a href="{% url 'urlnamelogin' %}">로그인</a>
    {% endif %}
```

if문을 활용해 로그인했을 때와 아닌 경우에 맞게 템플릿 언어를 작성하였다. 



이것으로 로그인과 로그아웃 기능 구현 완료! 