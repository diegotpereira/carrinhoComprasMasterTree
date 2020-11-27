package br.com.java.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import br.com.java.entity.Produto;

public class ProdutoInfo {
	
	private String codigo;
    private String nome;
    private double preco;
    
    private boolean novoProduto=false;
    
    // Upload file.
    private CommonsMultipartFile fileData;
    
	public ProdutoInfo() {
    }

	public ProdutoInfo(Produto produto) {
        this.codigo = produto.getCodigo();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
    }

	// Không thay đổi Constructor này,
	// nó được sử dụng trong Hibernate query.
	public ProdutoInfo(String codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

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

	public boolean isNovoProduto() {
		return novoProduto;
	}

	public void setNovoProduto(boolean novoProduto) {
		this.novoProduto = novoProduto;
	}

	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
}
