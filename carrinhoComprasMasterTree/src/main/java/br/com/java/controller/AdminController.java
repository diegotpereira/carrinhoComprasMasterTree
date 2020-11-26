package br.com.java.controller;

import javax.transaction.Transactional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
//Enable Hibernate Transaction.
@Transactional 
//Need to use RedirectAttributes
@EnableWebMvc
public class AdminController {
	
	 // GET: Show Login Page
    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login(Model model) {
 
        return "login";
    }
    
    @RequestMapping(value = { "/accountInfo" }, method = RequestMethod.GET)
    public String accountInfo(Model model) {
 
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.isEnabled());
 
        model.addAttribute("userDetails", userDetails);
        return "accountInfo";
    }
	
	  // GET: Show product.
    @RequestMapping(value = { "/produto" }, method = RequestMethod.GET)
    public String produto(Model model, @RequestParam(value = "codigo", defaultValue = "") String code) {
//        ProductInfo productInfo = null;
// 
//        if (code != null && code.length() > 0) {
//            productInfo = productDAO.findProductInfo(code);
//        }
//        if (productInfo == null) {
//            productInfo = new ProductInfo();
//            productInfo.setNewProduct(true);
//        }
//        model.addAttribute("productForm", productInfo);
        return "produto";
    }

}
