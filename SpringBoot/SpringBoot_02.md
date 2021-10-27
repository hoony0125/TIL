## SpringBoot에서 MySQL연동 설정

#### 1. pom.xml 

기본 dependency와 mybatis관련 dependency 외에 추가로 mysql 부분 추가

```xml
<dependency>
	<groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```



#### 2. application.properties

```properties
#port
server.port=3000

#아래부분이 sprbootsample01과 약간 다르다 
spring.datasource.hikari.driver-class-name= com.mysql.cj.jdbc.Driver
spring.datasource.hikari.jdbc-url=jdbc:mysql://localhost:3306/test   
spring.datasource.hikari.username=root
spring.datasource.hikari.password=mysql
```



#### 3. DatabaseConfig.java

SpringBoot_1에서 봤던것에 추가로 아래 Hikari가 포함된 함수를 두개 더해준다.

```java
package mul.com.a;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DatabaseConfig {	
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean
	public DataSource dataSource() {
		DataSource dataSource = new HikariDataSource(hikariConfig());
		System.out.println("dataSource:" + dataSource);
		
		return dataSource;
	}
	

```



#### 6. temp.xml 작성 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="mul.com.a.dao.TempDao">

<select id="allTemp" resultType="mul.com.a.dto.TempDto">
	SELECT * FROM TEMPTABLE
</select>

</mapper>
```



#### 5. Dto, Dao, Service, Controller 작성 

#### 6. Application.java(메인)에서 서버 구동 후 크롬에서 url 입력해서 확인

5번, 6번은 MySQL과 연결이 잘 되었는지 리스트 형태로 데이터를 받아서 출력해보는 단계일 뿐이다. 

#### 