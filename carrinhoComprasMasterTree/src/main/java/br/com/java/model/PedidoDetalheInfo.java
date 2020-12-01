package br.com.java.model;

public class PedidoDetalheInfo {

	private String id;
	private String produtoCodigo;
	private String produtoNome;
	private int quantidade;
	private double preco;
	private double montante;
	
	public PedidoDetalheInfo() {}
	
	public PedidoDetalheInfo(String id, String produtoCodigo, String produtoNome, int quantidade, double preco, double montante) {
		this.id = id;
		this.produtoCodigo = produtoCodigo;
		this.produtoNome = produtoNome;
		this.quantidade = quantidade;
		this.preco = preco;
		this.montante = montante;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProdutoCodigo() {
		return produtoCodigo;
	}

	public void setProdutoCodigo(String produtoCodigo) {
		this.produtoCodigo = produtoCodigo;
	}

	public String getProdutoNome() {
		return produtoNome;
	}

	public void setProdutoNome(String produtoNome) {
		this.produtoNome = produtoNome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public double getMontante() {
		return montante;
	}

	public void setMontante(double montante) {
		this.montante = montante;
	}
}
