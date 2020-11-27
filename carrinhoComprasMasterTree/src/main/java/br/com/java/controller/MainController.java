package br.com.java.controller;



import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.java.dao.ProdutoDAO;
import br.com.java.entity.Produto;
import br.com.java.model.PaginationResult;
import br.com.java.model.ProdutoInfo;

@Controller
//Enable Hibernate Transaction.
@Transactional
//Need to use RedirectAttributes
@EnableWebMvc
public class MainController {
	
	@Autowired
    private ProdutoDAO produtoDAO;
	

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
        
        PaginationResult<ProdutoInfo> result = produtoDAO.queryProducts(page, //
                maxResult, maxNavigationPage, likeName);
 
        model.addAttribute("paginationProducts", result);
        
        return "listaProduto";
    }
	
	  @RequestMapping(value = { "/carrinhoCompras" }, method = RequestMethod.GET)
	    public String shoppingCartHandler(HttpServletRequest request, Model model) {
//	        CartInfo myCart = Utils.getCartInSession(request);
	 
//	        model.addAttribute("cartForm", myCart);
	        return "carrinhoCompras";
	    }
	  
		  @RequestMapping(value = { "/produtoImage" }, method = RequestMethod.GET)
		    public void produtoImagem(HttpServletRequest request, HttpServletResponse response, Model model,
		            @RequestParam("codigo") String codigo) throws IOException {
		        Produto produto = null;
		        if (codigo != null) {
		        	produto = this.produtoDAO.descProduto(codigo);
		        }
		        if (produto != null && produto.getImage() != null) {
		            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		            response.getOutputStream().write(produto.getImage());
		        }
		        response.getOutputStream().close();
		    }

}
