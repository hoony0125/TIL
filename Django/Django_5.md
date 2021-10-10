## 지원서 수정하기 첫번째 방법(CRUD 중 Update)



Update는 CRUD 중 Create와 유사한데, 다른 점은 Update의 경우에는 수정할 데이터의 id값을 받아야 한다는 것이다. Update를 두가지 방법으로 해볼텐데 여기서 소개하는 두번째 방법이 블로그에 작성한 Create와 유사하다. 참고로 첫번째 방법 또한 그와 유사하게 Create를 작성할 수 있기도 하다. 



첫번째 방법을 소개하면, edit함수와 update함수 2가지 함수를 이용한 방법이다. 

edit함수는 edit.html을 보여주기 위함이고, update함수는 데이터베이스에 수정사항을 적용하기 위함이다. 



**1. views.py에 edit함수, update함수 만들기** 

edit함수 작성

```python
def edit(request, each_id):
    edit_apply = Apply.objects.get(id=each_id)
    return render(request,'edit.html',{'edit_apply':edit_apply})
```



edit함수에서 get(id=each_id)를 이용해 데이터베이스의 Apply클래스에서 객체를 받아온다.

그리고 render함수를 통해 edit.html 페이지를 보여주고, 해당  페이지로 edit_apply를 보낸다.



update함수 작성

```python
def update(request, each_id):
        update_apply = Apply.objects.get(id=each_id)
        update_apply.name = request.POST['htmlname']
        update_apply.student_id = request.POST['htmlstudent_id']
        update_apply.major = request.POST['htmlmajor']
        update_apply.q1 = request.POST['htmlq1']
        update_apply.q2 = request.POST['htmlq2']
        update_apply.date = timezone.now() 
        update_apply.save()
        return redirect('urlnamedetail',update_apply.id)
```



**2. edit.html파일을 만들고 템플릿 작성하기**

```python
<form action="{% url 'urlnameupdate' edit_apply.id %}" method="POST">
    {% csrf_token %}     
    <h3>지원서 수정페이지입니다.</h3><br>
    이름: <input type="text" name="htmlname" value="{{edit_apply.name}}"> <br>
    학번: <input type="text" name="htmlstudent_id" value="{{edit_apply.student_id}}"> <br>
    학과: <input type="text" name="htmlmajor" value="{{edit_apply.major}}"> <br>
    q1: <textarea name="htmlq1" id="" cols="30" rows="10">{{edit_apply.q1}}</textarea><br>
    q2: <textarea name="htmlq2" id="" cols="30" rows="10">{{edit_apply.q2}}</textarea><br>
    <button type="submit">제출하기</button>
</form> 
```

value값을 넣어주어야 기존에 저장되어 있는 apply의 객체들이 들어가게 된다. (공란에 새로운 것을 작성하는게 아니라, 수정하는 것이므로) 이 때, textarea의 경우 value를 입력할 때, input태그와는 다르게 태그와 태그 사이에 적어준다. 

그리고 form action에는 url을 적어주는데, 이 때, views.py에서 넘겨준 키의 이름으로 id도 같이 적어주어야 한다. 
 




**3. urls.py에서 url path로 url 연결하기** 

```python
from django.urls import path
from apply.views import *

urlpatterns = [
    path('new',new,name='urlnamenew'),
    path('detail<str:each_id>',detail,name='urlnamedetail'),
    path('readall',readall,name='urlnamereadall'),
    path('edit<str:each_id>',edit,name='urlnameedit'),
    path('update<str:each_id>',update,name='urlnameupdate'),
]   
```

edit와 update의 url path를 적을 때는 path converter를 사용해주어야 한다.(매개변수를 받으려면 무조건 있어야 하기 때문) 





**4. detail.html에 수정할 수 있도록 a태그를 넣어준다.**

```python
<a href="{% url 'urlnameedit' views_apply.id %}">수정하기</a>
```

 여기서 views_apply.id는 views.py에서 detail함수에서 지정한 키이다. 음...다른 키로 다 해봤는데, 이 키를 제외하고는 모두 에러가 난다. 왜 detail함수에 있는 키의 id로 해야되는지는 잘 모르겠다악! 

이렇게 첫번째 방법은 완료!
