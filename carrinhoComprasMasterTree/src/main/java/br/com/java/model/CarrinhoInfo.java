package br.com.java.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarrinhoInfo {
	
	private int pedidoNum;
	private ClienteInfo clienteInfo;
	private final List<CarrinhoLinhaInfo> carrinhoLinhas = new ArrayList<CarrinhoLinhaInfo>();
	
	
	
	public CarrinhoInfo() {}

	public int getPedidoNum() {
		return pedidoNum;
	}

	public void setPedidoNum(int pedidoNum) {
		this.pedidoNum = pedidoNum;
	}

	public ClienteInfo getClienteInfo() {
		return clienteInfo;
	}

	public void setClienteInfo(ClienteInfo clienteInfo) {
		this.clienteInfo = clienteInfo;
	}

	public List<CarrinhoLinhaInfo> getCarrinhoLinhas() {
		return carrinhoLinhas;
	}
	private CarrinhoLinhaInfo encontrarLinhaPorCodigo(String codigo) {
		
		for (CarrinhoLinhaInfo linha: this.carrinhoLinhas) {
			if (linha.getProdutoInfo().getCodigo().equals(codigo)) {
				return linha;
			}
		}
		return null;
	}	
	public void adicionarProduto(ProdutoInfo produtoInfo, int quantidade) {
		CarrinhoLinhaInfo linha = this.encontrarLinhaPorCodigo(produtoInfo.getCodigo());
		
		if (linha == null) {
			linha = new CarrinhoLinhaInfo();
			linha.setQuantidade(0);
			linha.setProdutoInfo(produtoInfo);
			this.carrinhoLinhas.add(linha);
		}
		int novaQuantidade = linha.getQuantidade() + quantidade;
		
		if (novaQuantidade <=0) {
			this.carrinhoLinhas.remove(linha);
		}else {
			linha.setQuantidade(novaQuantidade);
		}
	}
	public void validate() {}
	
	public void alterarProduto(String codigo, int quantidade) {
		CarrinhoLinhaInfo linha = this.encontrarLinhaPorCodigo(codigo);
		
		if (linha != null) {
			if (quantidade <= 0) {
				this.carrinhoLinhas.remove(linha);
			}
		} else {
			linha.setQuantidade(quantidade);

		}
	}
	public void removerProduto(ProdutoInfo produtoInfo) {
		CarrinhoLinhaInfo linha = this.encontrarLinhaPorCodigo(produtoInfo.getCodigo());
		if (linha != null) {
			this.carrinhoLinhas.remove(linha);
		}
	}
	public boolean isEmpty() {
		return this.carrinhoLinhas.isEmpty();
	}
	public boolean isValidCliente() {
		return this.clienteInfo != null && this.clienteInfo.isValida();
	}
	public int getQuantidadeTotal() {
		int quantidade = 0	;
		for (CarrinhoLinhaInfo linha : this.carrinhoLinhas) {
			quantidade += linha.getQuantidade();
		}
		return quantidade;
	}
	public double getMontanteTotal() {
		double total=0;
		
		for (CarrinhoLinhaInfo linha : this.getCarrinhoLinhas()) {
			total += linha.getMontante();
		}
		return total;
	}
	public void alterarQuantidade(CarrinhoInfo carrinhoForm) {
		if (carrinhoForm != null) {
			List<CarrinhoLinhaInfo> linhas =  carrinhoForm.getCarrinhoLinhas();
			for(CarrinhoLinhaInfo linha : linhas) {
				this.alterarProduto(linha.getProdutoInfo().getCodigo(), linha.getQuantidade());
			}
		}
	}

}
