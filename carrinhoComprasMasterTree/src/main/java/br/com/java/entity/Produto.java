package br.com.java.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_Produtos")
public class Produto implements Serializable{
	
	private static final long serialVersionUID = -1000119078147252957L;
	
	private String codigo;
	private String nome;
	private double preco;
	private byte[] image;
	private Date criarData;
	
	public Produto() {}
	
	
	@Id
	@Column(name = "Codigo", length = 20, nullable = false)
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "Nome", length = 255, nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
    
	@Column(name = "Preco", nullable = false)
	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	@Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "criar_Data", nullable = false)
	public Date getCriarData() {
		return criarData;
	}

	public void setCriarData(Date criarData) {
		this.criarData = criarData;
	}
}
