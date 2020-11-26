package br.com.java.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ProdutoInfo {
	
	private String codigo;
    private String nome;
    private double preco;
    
    private boolean novoProdut0=false;
    
    // Upload file.
    private CommonsMultipartFile fileData;
    
    public ProdutoInfo() {}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public boolean isNovoProdut0() {
		return novoProdut0;
	}

	public void setNovoProdut0(boolean novoProdut0) {
		this.novoProdut0 = novoProdut0;
	}

	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
}
