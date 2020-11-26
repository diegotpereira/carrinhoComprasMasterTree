package br.com.java.dao;

import org.hibernate.SessionFactory;

import br.com.java.entity.Conta;

public interface ContaDAO {
	
	public void setSessionFactory(SessionFactory sessionFactory);
	
	public Conta descConta(String userName );

}
