package br.com.java.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;

import br.com.java.entity.Conta;

@Resource(name="sessionFactory")
public interface ContaDAO {
	
//	public void setSessionFactory(SessionFactory sessionFactory);
	
	public Conta descConta(String userName );

}
