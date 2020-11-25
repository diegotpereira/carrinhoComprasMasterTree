package br.com.java.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.java.entity.Conta;

@Transactional
public class ContaDAOImpl implements ContaDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Conta findAccount(String userName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Conta.class);
		crit.add(Restrictions.eq("userName", userName));
		return (Conta) crit.uniqueResult();
	}

}
