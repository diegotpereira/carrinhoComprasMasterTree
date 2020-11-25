package br.com.java.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
public class MainController {

	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping({ "/listaProduto" })
    public String listProductHandler(Model model, //
            @RequestParam(value = "name", defaultValue = "") String likeName,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 5;
        final int maxNavigationPage = 10;
 
//        PaginationResult<ProductInfo> result = productDAO.queryProducts(page, //
//                maxResult, maxNavigationPage, likeName);
 
//        model.addAttribute("paginationProducts", result);
        return "listaProduto";
    }
	
	  @RequestMapping(value = { "/carrinhoCompras" }, method = RequestMethod.GET)
	    public String shoppingCartHandler(HttpServletRequest request, Model model) {
//	        CartInfo myCart = Utils.getCartInSession(request);
	 
//	        model.addAttribute("cartForm", myCart);
	        return "carrinhoCompras";
	    }

}
