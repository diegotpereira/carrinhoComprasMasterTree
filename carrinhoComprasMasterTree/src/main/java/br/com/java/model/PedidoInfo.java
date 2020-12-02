package br.com.java.model;

import java.util.Date;
import java.util.List;

public class PedidoInfo {
	
	private String id;
	private Date pedidoData;
	private int pedidoNum;
	private double montante;
	private String clienteNome;
	private String clienteEndereco;
	private String clienteEmail;
	private String clienteTelefone;
	
	private List<PedidoDetalheInfo> detalhes;
	
	public PedidoInfo() {}
	
	public PedidoInfo(String id, Date pedidoData, int pedidoNum, double montante, //
			String clienteNome,String clienteEndereco, String clienteEmail, String clienteTelefone) {
		this.id = id;
		this.pedidoData = pedidoData;
		this.pedidoNum = pedidoNum;
		this.montante = montante;
		this.clienteNome = clienteNome;
		this.clienteEndereco = clienteEndereco;
		this.clienteEmail = clienteEmail;
		this.clienteTelefone =clienteTelefone; 
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getPedidoData() {
		return pedidoData;
	}

	public void setPedidoData(Date pedidoData) {
		this.pedidoData = pedidoData;
	}

	public int getPedidoNum() {
		return pedidoNum;
	}

	public void setPedidoNum(int pedidoNum) {
		this.pedidoNum = pedidoNum;
	}

	public double getMontante() {
		return montante;
	}

	public void setMontante(double montante) {
		this.montante = montante;
	}

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public String getClienteEndereco() {
		return clienteEndereco;
	}

	public void setClienteEndereco(String clienteEndereco) {
		this.clienteEndereco = clienteEndereco;
	}

	public String getClienteEmail() {
		return clienteEmail;
	}

	public void setClienteEmail(String clienteEmail) {
		this.clienteEmail = clienteEmail;
	}

	public String getClienteTelefone() {
		return clienteTelefone;
	}

	public void setClienteTelefone(String clienteTelefone) {
		this.clienteTelefone = clienteTelefone;
	}

	public List<PedidoDetalheInfo> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<PedidoDetalheInfo> detalhes) {
		this.detalhes = detalhes;
	}

}
