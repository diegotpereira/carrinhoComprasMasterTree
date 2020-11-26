package br.com.java.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.java.entity.Conta;
@Repository
@Transactional 
public class ContaDAOImpl implements ContaDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Conta descConta(String userName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Conta.class);
		crit.add(Restrictions.eq("userName", userName));
		return (Conta) crit.uniqueResult();
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		// TODO Auto-generated method stub
		Session session;

		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
	}

}
