package mul.com.a;

import java.util.Date; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller	// <- annotation
public class HomeController {
	
	private static Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("home")
	public String helloMethod() {
	//	System.out.println("HomeController helloMethod()");		
		logger.info("HomeController helloMethod() " + new Date());
		
		return "home";		// <- home.jsp로 가라!
	}
	
	@RequestMapping(value = "hello.do")
	public String hello(Model model) {	// Model <- 짐을 포장하기 위한 interface
		logger.info("HomeController hello() " + new Date());
		
		String name = "홍길동";		
		model.addAttribute("name", name); // 짐싸! == setAttribute
		
		return "hello";
	}
	
	/*
	@RequestMapping(value = "home.do", method = RequestMethod.GET)
	public String home(String name, int age) {
		logger.info("HomeController home() " + new Date());
		
		System.out.println("name:" + name);
		System.out.println("age:" + age);
		
		return "hello";
	}
	*/
	
	@RequestMapping(value = "home.do", method = RequestMethod.GET)
	public String home(Model model, Human human) {
		logger.info("HomeController home() " + new Date());
		
		System.out.println("name:" + human.getName());
		System.out.println("age:" + human.getAge());
		
		model.addAttribute("human", human);
		
		return "hello";
	}
	
}









