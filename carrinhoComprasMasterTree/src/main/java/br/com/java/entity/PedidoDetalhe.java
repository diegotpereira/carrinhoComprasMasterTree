package br.com.java.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;

@Entity
@Table(name = "tb_Detalhe_Pedido")
public class PedidoDetalhe implements Serializable {
	

    private static final long serialVersionUID = 7550745928843183535L;
 
    private String id;
    private Pedido pedido;
    private Produto produto;
    private int quantidade;
    private double preco;
    private double montante;
    
    @Id
    @Column(name = "ID", length = 50, nullable = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PEDIDO_ID", nullable = false, //
    foreignKey = @ForeignKey(name = "PEDIDO_DETALHE_PED_FK") )
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUTO_ID", nullable = false, //
			foreignKey = @ForeignKey(name = "PEDIDO_DETALHE_PROD_FK"))
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	@Column(name = "Quantidade", nullable = false)
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	@Column(name = "Preco", nullable = false)
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	  @Column(name = "Montante", nullable = false)
	public double getMontante() {
		return montante;
	}
	public void setMontante(double montante) {
		this.montante = montante;
	}
    
    

}
