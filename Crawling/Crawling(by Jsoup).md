## Crawling(크롤링)

#### Jsoup으로 Crawling하기

**1. 기본 설정**

- Dynamic Web Project로 프로젝트 시작

- main 패키지에 MainClass.java 생성 

- Maven Repository에서 Jsoup Java HTML Parser를 검색해서 .jar 파일 받고, web app > WEB-INF > lib에 가져다놓기

  \-  스프링으로 하는 경우에는 dependency를 pom.xml에 복붙하면 된다!

  

### 2. MainClass.java 

```java
package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainClass {

	public static void main(String[] args) throws Exception{
		/*
		 Crawling : 데이터 취합, web에서 데이터를 수집
		 			naver, facebook 등
		 jsoup, python
		 */

		
		Document doc = Jsoup.connect("http://www.cgv.co.kr/movies/").get();
		/*
			<div class="box-contents">
                <a href="/movies/detail-view/?midx=84690">
                   <strong class="title">베놈 2-렛 데어 비 카니지</strong>
		*/
		Elements titles = doc.select("div.box-contents strong.title");
		
		/*
			<div class="score">
                <strong class="percent">예매율<span>68.6%</span></strong>
                <!-- 2020.05.07 개봉전 프리에그 노출, 개봉후 골든에그지수 노출변경 (적용 범위1~ 3위)-->
                <div class='egg-gage small'>
                     <span class='egg good'></span>
                     <span class='percent'>80%</span>
                </div>
            </div>	
		*/
		Elements percents = doc.select("div.box-contents div.score strong.percent span");
		
		for (int i = 0; i < 7; i++) {
			Element title = titles.get(i);
			Element percent = percents.get(i);
			
			String ti = title.text();
			String pe = percent.text();
			
			System.out.println(ti + " " + pe);
		}
		
	}
}
```



### 3. MovieDto.java

```java
package dto;

public class MovieDto {
	
	private String title;
	private double percent;
	
	public MovieDto() {
	}

	public MovieDto(String title, double percent) {
		super();
		this.title = title;
		this.percent = percent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	@Override
	public String toString() {
		return "MovieDto [title=" + title + ", percent=" + percent + "]";
	}	
}
```



### 4. MovieChart.java

```java
package movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dto.MovieDto;

public class MovieChart {

	public static List<MovieDto> getCGVdata() throws IOException {
		
		Document doc = Jsoup.connect("http://www.cgv.co.kr/movies/").get();
		
		Elements titles = doc.select("div.box-contents strong.title");
		Elements percents = doc.select("div.box-contents div.score strong.percent span");
		
		List<MovieDto> list = new ArrayList<MovieDto>();
		
		for (int i = 0; i < 7; i++) {
			Element title = titles.get(i);
			Element percent = percents.get(i);
			
			String ti = title.text();
			String pe = percent.text();
			
			double dPercent = Double.parseDouble( pe.replace("%", "") );
			// %를 제거하고 숫자로 인식하도록 parseDouble을 해준다. 
			MovieDto dto = new MovieDto(ti, dPercent);
			
			list.add(dto);
		}
		
		return list;
	}
}
```



### 5. movieChart.jsp 

- HIGHCHARTS 검색 후 > View Demos > Pie chart > EDIT IN CODEPEN > HTML(<body>), CSS(<style>), JS(<script>) 받아오기 

- JS 안에 영화 예매율 데이터를 받아올 수 있도록 수정

```jsp
<%@page import="movie.MovieChart"%>
<%@page import="dto.MovieDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<MovieDto> list = MovieChart.getCGVdata();
	for(MovieDto m : list){
		System.out.println(m.toString());
	}
	
	// list -> json
	String jsonData = "[";
	
	for(MovieDto m : list){
		jsonData += "{ name:\"" + m.getTitle() + "\", y:" + m.getPercent() + "}, ";
	}
	
	jsonData = jsonData.substring(0, jsonData.lastIndexOf(","));	
	// Json데이터의 마지막에는 쉼표가 오면 안되기 때문에 데이터 맨 끝의 바로 앞에 있는 ,를 지우기 위한 코드
	jsonData += "]";
	
	System.out.println(jsonData);
	
	request.setAttribute("jsonData", jsonData);
%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>

<style type="text/css">
.highcharts-figure, .highcharts-data-table table {
  min-width: 320px; 
  max-width: 800px;
  margin: 1em auto;
}
.highcharts-data-table table {
	font-family: Verdana, sans-serif;
	border-collapse: collapse;
	border: 1px solid #EBEBEB;
	margin: 10px auto;
	text-align: center;
	width: 100%;
	max-width: 500px;
}
.highcharts-data-table caption {
  padding: 1em 0;
  font-size: 1.2em;
  color: #555;
}
.highcharts-data-table th {
	font-weight: 600;
  padding: 0.5em;
}
.highcharts-data-table td, .highcharts-data-table th, .highcharts-data-table caption {
  padding: 0.5em;
}
.highcharts-data-table thead tr, .highcharts-data-table tr:nth-child(even) {
  background: #f8f8f8;
}
.highcharts-data-table tr:hover {
  background: #f1f7ff;
}
input[type="number"] {
	min-width: 50px;
}
</style>

</head>
<body>


<figure class="highcharts-figure">
  <div id="container"></div>  
</figure>



<script type="text/javascript">
Highcharts.chart('container', {
	  chart: {
	    plotBackgroundColor: null,
	    plotBorderWidth: null,
	    plotShadow: false,
	    type: 'pie'
	  },
	  title: {
	    text: '영화 예매율'
	  },
	  tooltip: {
	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	  },
	  accessibility: {
	    point: {
	      valueSuffix: '%'
	    }
	  },
	  plotOptions: {
	    pie: {
	      allowPointSelect: true,
	      cursor: 'pointer',
	      dataLabels: {
	        enabled: true,
	        format: '<b>{point.name}</b>: {point.percentage:.1f} %'
	      }
	    }
	  },
	  series: [{
	    name: 'Brands',
	    colorByPoint: true,
	    data: <%=request.getAttribute("jsonData") %>
	  }]
	});
</script>

</body>
</html>







```



![image-20211016170115280](Crawling(by%20Jsoup).assets/image-20211016170115280.png)
