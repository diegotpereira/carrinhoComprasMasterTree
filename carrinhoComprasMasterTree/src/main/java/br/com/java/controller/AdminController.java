package br.com.java.controller;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.java.dao.PedidoDAO;
import br.com.java.dao.ProdutoDAO;
import br.com.java.model.PaginationResult;
import br.com.java.model.PedidoDetalheInfo;
import br.com.java.model.PedidoInfo;
import br.com.java.model.ProdutoInfo;
import br.com.java.validator.ProdutoInfoValidator;

@Controller
//Enable Hibernate Transaction.
@Transactional 
//Need to use RedirectAttributes
@EnableWebMvc
public class AdminController {
	
	@Autowired
	private PedidoDAO pedidoDAO;
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private ProdutoInfoValidator produtoInfoValidator;
	
	// Configurated In ApplicationContextConfig.
    @Autowired
    private ResourceBundleMessageSource messageSource;
 
	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == ProdutoInfo.class) {
			dataBinder.setValidator(produtoInfoValidator);
			// For upload Image.
			dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		}
	}
	
	 // GET: Show Login Page
    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login(Model model) {
 
        return "login";
    }
    
    @RequestMapping(value = { "/contaInfo" }, method = RequestMethod.GET)
    public String contaInfo(Model model) {
 
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.isEnabled());
 
        model.addAttribute("userDetails", userDetails);
        return "contaInfo";
    }
    @RequestMapping(value = {"/pedidoLista"}, method = RequestMethod.GET)
    public String pedidoLista(Model model,//
    		@RequestParam(value = "page", defaultValue = "1")String pageStr) {
    	
    	int page = 1;
    	
    	try {
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
			// TODO: handle exception
		}
			final int MAX_RESULT = 5;
			final int MAX_NAVIGATION_PAGE = 10;
			
			PaginationResult<PedidoInfo> paginationResult = pedidoDAO.listaPedidoInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
			
			model.addAttribute("paginationResult", paginationResult);
			return "pedidoLista";
    
    }
	
	  // GET: Show product.
    @RequestMapping(value = { "/produto" }, method = RequestMethod.GET)
    public String produto(Model model, @RequestParam(value = "codigo", defaultValue = "") String codigo) {
		ProdutoInfo produtoInfo = null;

		if (codigo != null && codigo.length() > 0) {
			produtoInfo = produtoDAO.descProdutoInfo(codigo);
		}
		if (produtoInfo == null) {
			produtoInfo = new ProdutoInfo();
			produtoInfo.setNovoProduto(true);
		}
		model.addAttribute("produtoForm", produtoInfo);
		return "produto";
	}
    // POST: Salvar produto
    @RequestMapping(value = { "/produto" }, method = RequestMethod.POST)
    // Avoid UnexpectedRollbackException (See more explanations)
    @Transactional(propagation = Propagation.NEVER)
    public String salvarProduto(Model model, //
            @ModelAttribute("produtoForm") @Validated ProdutoInfo produtoInfo, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
 
        if (result.hasErrors()) {
            return "produto";
        }
        try {
            produtoDAO.salvar(produtoInfo);
        } catch (Exception e) {
            // Need: Propagation.NEVER?
        	System.out.println(e.getMessage());
            String message = e.getMessage();
            model.addAttribute("message", message);
            // Show product form.
            return "produto";
 
        }
        return "redirect:/listaProduto";
    }
    @RequestMapping(value = {"/pedido"}, method = RequestMethod.GET)
    public String pedidoMostrar(Model model, @RequestParam ("pedidoID") String pedidoId) {
    	
    	PedidoInfo pedidoInfo = null;
    	
    	if (pedidoId != null) {
			pedidoInfo = this.pedidoDAO.getPedidoInfo(pedidoId);
		}
    	if (pedidoInfo == null) {
			return "redirect:/pedidoLista";
		}
    	List<PedidoDetalheInfo> detalhes = this.pedidoDAO.listarInformacoesDetalhesPedido(pedidoId);
    	pedidoInfo.setDetalhes(detalhes);
    	
    	model.addAttribute("pedidoInfo", pedidoInfo);
    	
    	return "pedido";
    }

}
