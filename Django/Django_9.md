## 회원가입 페이지 만들기(USER확장)(PhoneNumberField 사용) 

회원가입은 django의 기본적으로 주어지는 user model 을 사용해 기능 구현이 가능하다.

다만, User model을 사용하면, 기본적인 정보만으로만 회원가입이 가능하다.(아이디, 비밀번호, 비밀번호 확인) 그러나 거의 대부분의 웹사이트들은 휴대전화번호, 이메일, 주소지 등 다양한 정보를 받아서 회원가입이 진행된다. 

따라서 User model 대신 AbstractUser을 상속받은 CustomUser라는 모델을 내가 재정의 해서 아이디와 비밀번호에 추가로 학번, 학과, 전화번호를 받아보도록 하려고 한다. 이 때, 전화번호는 나라의 고유번호를 반영한 형태(+8210-1234-5678)로 받아서 회원가입 기능을 한번 구현해보도록 하겠다. 

참고로 AbstractBaseUser모델을 상속 받아 내가 새롭게 모델을 재정의할 수도 있는데, 이 경우에는 e-mail을 아이디로 사용한다거나, 인증 절차를 거친다거나 그러한 경우에 사용한다고 한다. 





이제 본격적으로 시작해보자.



**1.  models.py에 받고자 하는 데이터들을 작성한다.**

```python
from django.db import models
from django.contrib.auth.models import AbstractUser
from phonenumber_field.modelfields import PhoneNumberField

class CustomUser(AbstractUser):
    name = models.CharField(max_length=10)
    student_id = models.CharField(max_length=10)
    major = models.CharField(max_length=20)
    phone_number = PhoneNumberField()
```

여기서 PhoneNumberField를 받아야 +8210-1234-5678의 형태로 전화번호를 받을 수 있다. 





**2. settings.py에서 CustomuUser model을 재정의했다고 알려주어야 하는데, 그래야 default로 User model 대신 CustomUser model을 받게 된다.** 

```python
AUTH_USER_MODEL = "account.CustomUser"                      # "앱이름.  재정의한 model명"
```



**3. makemigrations와 migrate** 

model을 새롭게 짰으니, makemigrations와 migrate를 해줘야 하는데, makemigrations까지는 잘 될 수 있으나, migrate 명령어를 실행하면, Migration admin.0001_initial is applied before its dependency ~이런 형태의 에러가 날 수 있다. 이 에러의 해결방안은 간단하다. admin과 관련된 에러인데 settings.py의 INSTALLED_APPS에서 admin 관련 코드와 urls.py에서 admin 관련 코드들을 잠시 주석처리하고 migrate를 하면 정상적으로 작동한다. 이 후에 주석처리를 해제해주면 된다. 





**4. forms.py 파일 만들고, UserCreationForm 상속 받아 Form 재정의하기**

```python
from .models import CustomUser
from django.contrib.auth.forms import UserCreationForm

class RegisterForm(UserCreationForm):
    class Meta:
        model = CustomUser
        fields = ['username', 'password1', 'password2', 'name', 'student_id', 'major', 'phone_number']

        labels = {
            'username': '아이디',
            'name': '이름',
            'student_id': '학번',
            'major': '학과',
            'phone_number': '전화번호',
        }
```

model이 바뀌었기 때문에 회원가입을 하기 위해서는 User model에서 회원가입을 하는 UserCreationForm은 더 이상 사용할 수 없다. 그러므로 UserCreationForm을 상속받아 RegisterForm을 재정의해서 이를 이용해 회원가입 함수를 작성해야 한다. 





**5. signup.html 파일을 만들고 템플릿 작성하기**

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
    <form action="{%url 'urlnamesignup'%}" method="POST">   #url은 미리 작성! 
    {% csrf_token %}
        {{views_signup_form.as_p}}
        <div style="color:red;">
            <br>**전화번호는 아래와 같은 형식으로 가입 부탁드립니다. <br>
            전화번호가 010-1234-1234인 경우에 -> +821012341234 <br><br>
        </div>         
#국가 고유번호로 받아오되 오류방지를 위해 긴급처방,,다소 보완이 필요한 부분
        <button type="submit">회원가입</button>
    </form>
</div>
{% endblock %}
```



**6. views.py에 함수 작성하기** 

```python
from account.forms import RegisterForm
from account.models import CustomUser
from django.contrib import messages

def signup_view(request):
    if request.method =='POST': 
        new_signup_form = RegisterForm(request.POST)

        if CustomUser.objects.filter(phone_number = request.POST['phone_number']).exists():   #전화번호 중복체크
                messages.info(request,'이미 가입되어 있는 번호입니다.')
                return redirect('urlnamesignup')

        if new_signup_form.is_valid():
            user = new_signup_form.save()
            login(request, user)
            return redirect('urlnamehome')
        else:
            if CustomUser.objects.filter(username = request.POST['username']).exists():   #아이디 중복체크
                messages.info(request,'중복되는 ID가 존재합니다!')
                return redirect('urlnamesignup')

            elif request.POST['password1'] != request.POST['password2']:                  #비밀번호=비밀번호 확인
                messages.info(request,'비밀번호와 비밀번호 확인이 일치하지 않습니다!')
                return redirect('urlnamesignup')
            
            elif len(request.POST['password1']) < 8:                                       #비밀번호 8자리 이상 
                messages.info(request,'비밀번호는 8자리 이상으로 설정해주세요!')
                return redirect('urlnamesignup')

            elif request.POST['password1'].isdigit():
                messages.info(request,'비밀번호는 숫자로만 설정할 수 없습니다!')
                return redirect('urlnamesignup')

            elif request.POST['username'] in request.POST['password1']:
                messages.info(request,'비밀번호에 아이디가 포함될 수 없습니다')
                return redirect('urlnamesignup')

            elif CustomUser.objects.filter(student_id = request.POST['student_id']).exists():   #학번 중복체크
                messages.info(request,'이미 가입되어 있는 학번입니다.')
                return redirect('urlnamesignup')
            else:
                messages.info(request,'알 수 없는 에러가 발생했습니다. 관리자에게 문의하세요')     # 그 외 에러 발생 시
                return redirect('urlnamesignup')
    else:
        signup_form = RegisterForm()
        return render(request, 'signup.html',{'views_signup_form':signup_form})
```

기본적인 회원가입에 에러발생 시 메시지가 뜨도록 함수를 작성하였다. 





**7. urls.py에서 path연결해주기** 

```python
urlpatterns = [
    path('login/',login_view,name='urlnamelogin'),
    path('signup/',signup_view,name='urlnamesignup'),
]
```

이렇게 하면, 회원가입도 완성! 





---

추가로 전화번호를 국가고유번호를 반영한 방식으로 받는 법을 설명하고자 한다. 

제목에서도 알 수 있다시피 **PhoneNumberField**를 받아오는 방법이다.



**1. 필요한 라이브러리 설치하기**

`pip install django-phonenumber-field[phonenumbers] `

`pip install django-phonenumber-field[phonenumberslite]`



**2. settings.py에 PhoneNumberField 추가하기** 

```python
INSTALLED_APPS = [
    ...
    'phonenumber_field',
    ...
]
```



**3. 위에서 본 것처럼 account앱의 models.py에서 PhoneNumberField import 및 작성**

```python
from django.db import models
from django.contrib.auth.models import AbstractUser
from phonenumber_field.modelfields import PhoneNumberField

class CustomUser(AbstractUser):
    name = models.CharField(max_length=10)
    student_id = models.CharField(max_length=10)
    major = models.CharField(max_length=20)
    phone_number = PhoneNumberField()
```

이렇게 하면 전화번호를 +821012345678의 형태로 저장받게 된다! 

다만, 회원가입을 하는 사용자가 그냥 본인의 번호만 입력하는 것이 아니기 때문에 반드시 위의 형태로 +부터 입력을 해야하는 번거로움도 있기 때문에 추가적인 보완이 필요하다. 



이렇게 회원가입 기능 구현을 완성했다. 앞서 처음 배우면서 클론 코딩할 때, 팀 프로젝트로 코딩할 때 모두 라이브러리나, 상속, form 사용 등 이론이나 용어 대한 이해는 거의 이뤄지지 않은 채로 코드만 따라 쳤는데, 이렇게 혼자 코딩을 하며 복습도 하고, 구글링도 해보는 과정에서 점점 이해하는 것도 많아지고, 익히는 것도 많아지는게 스스로 느껴져서 다행이고, 뿌듯하다. 앞으로  페이지네이션, 정적파일 적용하기, 인스타그램 DM기능 구현 등에 대해서도 포스팅할 예정인데, 이러한 과정을 통해 스스로 한층 더 성장할 수 있을 것 같다는 기대감이 든다. :) 