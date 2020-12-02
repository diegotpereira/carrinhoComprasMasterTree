package br.com.java.controller;



import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String listaProdutoVenda(Model model, //
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
	public String listaProdutoCompra(HttpServletRequest request, Model model,//
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
	@RequestMapping({"/carrinhodeComprasRemoverProduto"})
	public String removerProdutoVenda(HttpServletRequest request, Model model,//
			@RequestParam(value= "codigo", defaultValue ="")String codigo) {
		Produto produto = null;
		if (codigo != null && codigo.length()>0) {
			produto = produtoDAO.descProduto(codigo);
		}
		if (produto !=null) {
			CarrinhoInfo carrinhoInfo = Utils.getCarrinhoInSession(request);
			
			ProdutoInfo produtoInfo = new ProdutoInfo();
			
			carrinhoInfo.removerProduto(produtoInfo);
		}
		return  "redirect:/carrinhoCompras";
	}
	
	@RequestMapping(value= {"/carrinhoCompras"}, method = RequestMethod.POST)
	public String carrinhoComprasAtualizarQtd(HttpServletRequest request,//
			Model model,//
	@ModelAttribute("carrinhoForm") CarrinhoInfo carrinhoForm){
		
		CarrinhoInfo carrinhoInfo = Utils.getCarrinhoInSession(request);
		carrinhoInfo.alterarQuantidade(carrinhoForm);
		
		return "redirect:/carrinhoCompras";
	}
	@RequestMapping(value= {"/carrinhoCompras"}, method = RequestMethod.GET)
	public String carrinhoComprasShow(HttpServletRequest request, Model model) {
		CarrinhoInfo meuCarrinho = Utils.getCarrinhoInSession(request);
		
		model.addAttribute("carrinhoForm", meuCarrinho);
		return "carrinhoCompras";
	}
	
	
	
	  @RequestMapping(value = { "/carrinhoComprasCliente" }, method = RequestMethod.GET)
	    public String carrinhoComprasClienteForm(HttpServletRequest request, Model model) {
		  
		  CarrinhoInfo carrinhoInfo = Utils.getCarrinhoInSession(request);
		  
		  if (carrinhoInfo.isEmpty()) {
			return "redirect:/carrinhoCompras";
		}
		  
		  ClienteInfo clienteInfo = carrinhoInfo.getClienteInfo();	
		  
		  if (clienteInfo == null) {
			clienteInfo = new ClienteInfo();
		}
		  model.addAttribute("clienteForm", clienteInfo);
		  return "carrinhoComprasCliente";
	  }
	  @RequestMapping(value= {"/carrinhoComprasCliente"}, method = RequestMethod.POST)
	  public String carrinhoComprasClienteSalvar(HttpServletRequest request,//
			  Model model,//
			  @ModelAttribute("clienteForm")@Validated ClienteInfo clienteForm,//
			  BindingResult result,//
			  final RedirectAttributes redirectAttributes) {
		  
		  if (result.hasErrors()) {
			clienteForm.setValida(false);
			
			return "carrinhoComprasCliente";
		}
		  
		  clienteForm.setValida(true);
		  CarrinhoInfo carrinhoInfo = Utils.getCarrinhoInSession(request);
		  
		  carrinhoInfo.setClienteInfo(clienteForm);
		  
		  return "redirect:/carrinhoComprasConfirmacao";
	  }
	  @RequestMapping(value = {"/carrinhoComprasConfirmacao"}, method = RequestMethod.GET)
      public String carrinhoComprasConfirmacaoRever(HttpServletRequest request, Model model) {
    	  
    	  CarrinhoInfo carrinhoInfo= Utils.getCarrinhoInSession(request);
    	  
    	  if (carrinhoInfo.isEmpty()) {
			return "redirect:/carrinhoCompras";
		}else if (!carrinhoInfo.isValidaCliente()) {
			return "redirect:/carrinhoComprasCliente";
		}
      return "carrinhoComprasConfirmacao";

      }
      @RequestMapping(value = {"/carrinhoComprasConfirmacao"}, method = RequestMethod.POST)
      @Transactional(propagation = Propagation.NEVER)
      public String carrinhoComprasConfirmacaoSalvar(HttpServletRequest request, Model model) {
    	  
    	  CarrinhoInfo carrinhoInfo = Utils.getCarrinhoInSession(request);
    	  
    	  if (carrinhoInfo.isEmpty()) {
			return "redirect:/carrinhoCompras";
		}else if (!carrinhoInfo.isValidaCliente()) {
			return "redirect:/carrinhoComprasCliente";
		}
    	  try {
			pedidoDAO.salvarPedido(carrinhoInfo);
		} catch (Exception e) {
			// TODO: handle exception
			return "carrinhoComprasConfirmacao";
		}
    	  Utils.armazenarUltimoCarrinhoPedidoNaSessao(request, carrinhoInfo);
    	  
    	  return "redirect:/carrinhoComprasFinalizar";
      
      }
      @RequestMapping(value= {"/carrinhoComprasFinalizar"}, method = RequestMethod.GET)
      public String carrinhoComprasFinalizar(HttpServletRequest request, Model model) {
    	  CarrinhoInfo ultimoCarrinhoPedido = Utils.getCarrinhoInSession(request);
    	  
    	  if (ultimoCarrinhoPedido ==null) {
			return "redirect:/carrinhoCompras";
		}
    	  return "carrinhoComprasFinalizar";
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
