## app별 urls.py (url 간략화 작업)

개발을 하다보면, 프로젝트 파일의 urls.py가 너무 길어지고, app별로 구분하기 번거롭고, 보기에도 편하지 않을 수 있다. 

따라서 url 간략화 작업을 통해 app별로 urls.py를 작성하면 한 눈에 보기 쉽기 때문에 간략화 작업을 할 때는 아래의 방법을 적용해보자



프로젝트의 urls.py는 다음과 같다 

```python
from django.contrib import admin
from django.urls import path, include                  #include를 import해줘야 한다. 
from apply.views import home                           #home은 home.html과 home함수를 작성할 때 import한 것이다.

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', home, name="urlnamehome"),
    path('apply/',include('apply.urls')),              #app이름(apply)으로 url주소를 적고, include를 적어준다.
```



다음 apply 라는 app에서 urls.py를 작성한 것이다. 

```python
from django.urls import path                            #큰 형태는 그냥 프로젝트urls.py에서 복붙한다.  
from apply.views import *                               #apply의 views에서 모든 함수(*)를 import해준다.

urlpatterns = [
    path('new',new,name='urlnamenew'),                              
    path('detail<str:each_id>',detail,name='urlnamedetail'),
    path('readall',readall,name='urlnamereadall'),
]   
```



게시글 하나하나의 볼륨이 너무 작은건 아닐까 싶기도 하지만, 일단 하나하나 배우는대로 이렇게 적어볼 예정이다..!