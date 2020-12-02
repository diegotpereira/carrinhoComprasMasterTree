package br.com.java.controller;



import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import b.com.java.util.Utils;
import br.com.java.dao.PedidoDAO;
import br.com.java.dao.ProdutoDAO;
import br.com.java.entity.Produto;
import br.com.java.model.CarrinhoInfo;
import br.com.java.model.ClienteInfo;
import br.com.java.model.PaginationResult;
import br.com.java.model.ProdutoInfo;
import br.com.java.validator.ClienteInfoValidator;

@Controller
//Enable Hibernate Transaction.
@Transactional
//Need to use RedirectAttributes
@EnableWebMvc
public class MainController {
	
	@Autowired
	public PedidoDAO pedidoDAO;
	
	@Autowired
    private ProdutoDAO produtoDAO;
	
	@Autowired
	private ClienteInfoValidator clienteInfoValidator;
	
	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		// For Cart Form.
		// (@ModelAttribute("cartForm") @Validated CartInfo cartForm)
		if (target.getClass() == CarrinhoInfo.class) {

		}
		// For Customer Form.
		// (@ModelAttribute("customerForm") @Validated CustomerInfo
		// customerForm)
		else if (target.getClass() == ClienteInfo.class) {
			dataBinder.setValidator(clienteInfoValidator);
		}

	}
	
	@RequestMapping("/403")
	public String accessDenied() {
		return "/403";
	}

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
	@RequestMapping({"/comprarProduto"})
	public String listaProdutoVenda(HttpServletRequest request, Model model,//
			@RequestParam(value="codigo", defaultValue ="")String codigo) {
		
		Produto produto = null;
		if (codigo != null && codigo.length()>0) {
			produto = produtoDAO.descProduto(codigo);
		}
		if (produto != null) {
			CarrinhoInfo carrinhoInfo = Utils.getCarrinhoInSession(request);
			
			ProdutoInfo produtoInfo = new ProdutoInfo(produto);
			
			carrinhoInfo.adicionarProduto(produtoInfo, 1);
		}
		return "redirect:/carrinhoCompras";
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
