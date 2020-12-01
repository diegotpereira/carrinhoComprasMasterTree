package br.com.java.dao;

import java.util.List;

import br.com.java.model.CarrinhoInfo;
import br.com.java.model.PaginationResult;
import br.com.java.model.PedidoDetalheInfo;
import br.com.java.model.PedidoInfo;

public interface PedidoDAO {
	
	public void salvarPedido(CarrinhoInfo carrinhoInfo);

	public PaginationResult<PedidoInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage);

	public PedidoInfo getPedidoInfo(String pedidoId);

	public List<PedidoDetalheInfo> listOrderDetailInfos(String pedidoId);

}
