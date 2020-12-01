package br.com.java.model;

public class CarrinhoLinhaInfo {
	
	private ProdutoInfo produtoInfo;
	private int quantidade;
	
	
	public CarrinhoLinhaInfo() {
		this.quantidade = 0;
	}

	public ProdutoInfo getProdutoInfo() {
		return produtoInfo;
	}

	public void setProdutoInfo(ProdutoInfo produtoInfo) {
		this.produtoInfo = produtoInfo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getMontante() {
		return this.produtoInfo.getPreco()* this.quantidade;
	}

}
