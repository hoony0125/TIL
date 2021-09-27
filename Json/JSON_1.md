## JSON 1 

#### JSON, 문자열로 받은 데이터 JSON으로 바꾸기, 

#### JSON파일 받아서 원하는 데이터 출력하기, 

#### HTML에서 JSON 받아서 원하는 데이터 출력하기, 

#### JSON Practice table로 출력하기 

### 1. JSON

```html
Json : Java Script Object Notation
					표기법
							
front end				back end 
javascript				java
jquery	 		
json ->	key : value		hashmap -> key : value	
		
[10, 20, 30]			{num:10, age:20, count:30}
 0   1   2 			 	key로 관리	
 
 
json 데이터 노드 여러개일 떄
		[
			{
				name:"홍길동",
				age:24,
				address:"서울시"
			},
			{
				name:"성춘향",
				age:16,
				address:"남원시"
			}		
		]	
		
json 데이터 노드가 단 하나일 때, 이렇게 적을 수도 있다.
		
			{
				name:"성춘향",
				age:16,
				address:"남원시"
			}
```



### 2. 문자열로 받은 데이터 JSON으로 바꾸기

```html
<body>
<p id ="demo"></p>
<script type="text/javascript">

let jsonData = [
					{
						"name":"홍길동",
						"age":24
					},
					{
						"name":"성춘향",
						"age":16
					}
				];
				
document.getElementById("demo").innerHTML = jsonData[0].name + "  " + jsonData[0].age;


let arrText = '{"name":"홍길동", "age":24, "주소":"서울시"}';
//alert(arrText);

let jsonObj = JSON.parse(arrText);	// 문자열을 parse를 통해 json으로 바꿈
alert(jsonObj);

//Json Object -> String
let str = JSON.stringify(jsonObj);	// Json을 문자열로 바꾸는 것 	
alert(str);

document.getElementById("demo").innerHTML = jsonObj.name + " " + jsonObj.age + " " + jsonObj.주소;
</script>
</body>
```



### 3. JSON파일 받아서 원하는 데이터 출력하기

```
<body>
<p id = "demo"></p>

<script type="text/javascript">

let xhttp = new XMLHttpRequest();

xhttp.onreadystatechange = function() {	// 서버에
	if(xhttp.readyState == 4 && xhttp.status == 200){
		jsonFunc(this);
		
	}
}
xhttp.open("GET", "data.json", true);
xhttp.send();

function jsonFunc( xhttp ) {
	
	//alert(xhttp.responseText);	// 문자열 뜬다!
	
	let str = xhttp.responseText;
	
	let json = JSON.parse(str);	// 이젠 JSON으로 뜬다! 
/* 	alert(json);
	alert(json.length); */

/* 	console.log(json[0].name);
	console.log(json[0].age);
	console.log(json[0].address);
	console.log(json[0].height); */
	
	let txt = "";
	
	for(i = 0; i < json.length; i++){
		txt += json[i].name + " " +
				json[i].age + " " +
				json[i].address + " " +
				json[i].height + " " + "<br>";
	}
	document.getElementById("demo").innerHTML = txt;
}

</script>
</body>
```



### 4.  HTML에서 JSON 받아서 원하는 데이터 출력하기

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p id = "demo"></p>

<script type="text/javascript">
let json = {
	    "quiz": {
	        "sport": {
	            "q1": {
	                "question": "Which one is correct team name in NBA?",
	                "options": [
	                    "New York Bulls",
	                    "Los Angeles Kings",
	                    "Golden State Warriros",
	                    "Huston Rocket"
	                ],
	                "answer": "Huston Rocket"
	            }
	        },
	        "maths": {
	            "q1": {
	                "question": "5 + 7 = ?",
	                "options": [
	                    "10",
	                    "11",
	                    "12",
	                    "13"
	                ],
	                "answer": "12"
	            },
	            "q2": {
	                "question": "12 - 8 = ?",
	                "options": [
	                    "1",
	                    "2",
	                    "3",
	                    "4"
	                ],
	                "answer": "4"
	            }
	        }
	    }
	};

// document.getElementById("demo").innerHTML = json.quiz["sport"].q1.question;
document.getElementById("demo").innerHTML = json.quiz.sport.q1.question;

document.getElementById("demo").innerHTML = json.quiz.sport.q1.options[1];
</script>
</body>
</html>
```



### 5. JSON Practice 테이블로 출력하기

- **jsonData.json**

  ```json
  [
  	{
  		"title" :"이것이 자바다",
  		"author":"신용권",
  		"price":"30000",
  		"date":"2020/09/27"
  	},
  	{
  		"title" : "웹을 다루는 기술",
  		"author":"이병승",
  		"price":"30000",
  		"date":"2019/09/17"
  	},
  	{
  		"title" :"이것이 오라클이다",
  		"author":"우재남",
  		"price":"22000",
  		"date":"2019/02/28"
  	},
  	{
  		"title" :"뇌를 자극하는 파이썬3",
  		"author":"박상현",
  		"price":"23000",
  		"date":"2020/05/14"
  	},
  	{
  		"title" :"챗봇 만들기",
  		"author":"정임수",
  		"price":"19000",
  		"date":"2020/03/12"
  	}
  ]
  ```

  

- **html파일**

  ```html
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  </head>
  <body>
  
  <!-- 
  자신이 읽은 책 : 5권
  제목(title), 저자(author), 가격(price), 발행연월일(2010/12/11)					
  xml, json 파일로 제작
  parsing -> 데이터
  시각화 -> table
   -->
  
  <div id="books"> 	<!-- 표를 받아주는 역할 -->
  </div> 
   
  
  <script type="text/javascript">
  
  let xhttp = new XMLHttpRequest();
  
  xhttp.onreadystatechange = function () {
  	if(this.readyState == 4 && this.status == 200){
  		jsonPars(this);	
  	}
  }
  xhttp.open("GET", "jsonData.json", true);
  xhttp.send();
  
  function jsonPars( xhttp ) {
  	
  	let jsonDoc = xhttp.responseText;
  //	alert(jsonDoc);
  	
  	let jsonObj = JSON.parse(jsonDoc);
  //	alert(jsonObj);
  
  //	alert(jsonObj[1].title);
  
  	let out = "<table border='1'>";
  	out += "<col width='50'><col width='180'><col width='100'><col width='100'><col width='120'>";
  	
  	out += "<tr>";
  	out += "<th>번호</th>";
  	for(k in jsonObj[0]){
  		out += "<th>" + k + "</th>";		
  	}
  	out += "</tr>";
  	
  	// datas
  	for(i = 0;i < jsonObj.length; i++){
  		out += "<tr>";	
  		
  		out += "<th>" + (i + 1) + "</th>";
  		
  		out += "<td>" + jsonObj[i].title + "</td>";
  		out += "<td>" + jsonObj[i].author + "</td>";
  		out += "<td>" + jsonObj[i].price + "</td>";
  		out += "<td>" + jsonObj[i].date + "</td>";		
  		
  		out += "</tr>";
  	}	
  	out += "</table>";	
  	
  	document.getElementById("books").innerHTML = out;	
  }
  
  </script> 
  </body>
  </html>
  ```

  

<img src="JSON_1.assets/image-20210927174858990.png" alt="image-20210927174858990" style="zoom:80%;" />