package br.com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class LoginController {
	
//	@RequestMapping(method = RequestMethod.GET)
//	public String sayHello(ModelMap model) {
////		model.addAttribute("greeting", "Hello World from Spring 4 MVC");
//		return "loginForm";
//	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginForm(ModelMap model) {
		return "loginForm";
	}
	
	

}
