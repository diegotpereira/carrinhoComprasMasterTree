package b.com.java.util;

import javax.servlet.http.HttpServletRequest;

import br.com.java.model.CarrinhoInfo;

public class Utils {
	
	public static CarrinhoInfo getCarrinhoInSession(HttpServletRequest request) {
		
		CarrinhoInfo carrinhoInfo = (CarrinhoInfo) request.getSession().getAttribute("meuCarrinho");
		
		if (carrinhoInfo ==null) {
			carrinhoInfo = new CarrinhoInfo();
			
			request.getSession().setAttribute("meuCarrinho", carrinhoInfo);
		}
		return carrinhoInfo;
		
	}
	public static void removerCarrinhoInSession(HttpServletRequest request) {
		request.getSession().removeAttribute("meuCarrinho");
	}
	public static void armazenarUltimoCarrinhoPedidoNaSessao(HttpServletRequest request, CarrinhoInfo carrinhoInfo) {
		request.getSession().setAttribute("UltimoCarrinhoPedido", carrinhoInfo);
	}
	public static CarrinhoInfo obterUltimoCarrinhoPedidoNaSessao(HttpServletRequest request) {
		return (CarrinhoInfo) request.getSession().getAttribute("ultimoCarrinhoPedido");
	}

}
