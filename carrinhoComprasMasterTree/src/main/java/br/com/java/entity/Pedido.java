package br.com.java.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_Pedidos", //
uniqueConstraints = { @UniqueConstraint(columnNames = "Pedido_Num") })
public class Pedido implements Serializable{
	
	private static final long serialVersionUID = -2576670215015463100L;
	
	private String id;
	private Date pedidoData;
	private int pedidoNum;
	private double montante;

	private String clienteNome;
	private String clienteEndereco;
	private String clienteEmail;
	private String clienteTelefone;
	
	@Id
	@Column(name = "ID", length = 50)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "Pedido_Data", nullable = false)
	public Date getPedidoData() {
		return pedidoData;
	}
	public void setPedidoData(Date pedidoData) {
		this.pedidoData = pedidoData;
	}
	@Column(name = "Pedido_Num", nullable = false)
	public int getPedidoNum() {
		return pedidoNum;
	}
	public void setPedidoNum(int pedidoNum) {
		this.pedidoNum = pedidoNum;
	}
	@Column(name = "Montante", nullable = false)
	public double getMontante() {
		return montante;
	}
	public void setMontante(double montante) {
		this.montante = montante;
	}
	@Column(name = "Cliente_Nome", length = 255, nullable = false)
	public String getClienteNome() {
		return clienteNome;
	}
	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}
	@Column(name = "Cliente_Endereco", length = 255, nullable = false)
	public String getClienteEndereco() {
		return clienteEndereco;
	}
	public void setClienteEndereco(String clienteEndereco) {
		this.clienteEndereco = clienteEndereco;
	}
	@Column(name = "Cliente_Email", length = 128, nullable = false)
	public String getClienteEmail() {
		return clienteEmail;
	}
	public void setClienteEmail(String clienteEmail) {
		this.clienteEmail = clienteEmail;
	}
	@Column(name = "Cliente_Telefone", length = 128, nullable = false)
	public String getClienteTelefone() {
		return clienteTelefone;
	}
	public void setClienteTelefone(String clienteTelefone) {
		this.clienteTelefone = clienteTelefone;
	}
	
	

}
