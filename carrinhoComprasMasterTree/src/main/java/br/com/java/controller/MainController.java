package br.com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
//Enable Hibernate Transaction.
@Transactional
//Need to use RedirectAttributes
@EnableWebMvc
public class MainController {
	
	 @RequestMapping("/")
	    public String home() {
	        return "index";
	    }

}
