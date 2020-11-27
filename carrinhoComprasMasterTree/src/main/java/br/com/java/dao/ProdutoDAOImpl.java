package br.com.java.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.java.entity.Produto;
import br.com.java.model.PaginationResult;
import br.com.java.model.ProdutoInfo;

@Transactional
public class ProdutoDAOImpl implements ProdutoDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Produto descProduto(String codigo) {
		Session session;// = sessionFactory.getCurrentSession();

		try {
			// Step-2: Implementation
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			// Step-3: Implementation
			session = sessionFactory.openSession();
		}
		Criteria crit = session.createCriteria(Produto.class);
		crit.add(Restrictions.eq("codigo", codigo));
		return (Produto) crit.uniqueResult();
	}

	@Override
	public ProdutoInfo descProdutoInfo(String codigo) {
		Produto produto = this.descProduto(codigo);
		if (produto == null) {
			return null;
		}
		return new ProdutoInfo(produto.getCodigo(), produto.getNome(), produto.getPreco());
	}

	@Override
	public void salvar(ProdutoInfo produtoInfo) {
		String codigo = produtoInfo.getCodigo();

		Produto produto = null;

		boolean isNew = false;
		if (codigo != null) {
			produto = this.descProduto(codigo);
		}
		if (produto == null) {
			isNew = true;
			produto = new Produto();
			produto.setCriarData(new Date());
		}
		produto.setCodigo(codigo);
		produto.setNome(produtoInfo.getNome());
		produto.setPreco(produtoInfo.getPreco());

		if (produtoInfo.getFileData() != null) {
			byte[] image = produtoInfo.getFileData().getBytes();
			if (image != null && image.length > 0) {
				produto.setImage(image);
			}
		}
		if (isNew) {
			this.sessionFactory.getCurrentSession().persist(produto);
		}
		// If error in DB, Exceptions will be thrown out immediately
		// Nếu có lỗi tại DB, ngoại lệ sẽ ném ra ngay lập tức
		this.sessionFactory.getCurrentSession().flush();
	}

	@Override
	public PaginationResult<ProdutoInfo> queryProducts(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		String sql = "Select new " + ProdutoInfo.class.getName() //
				+ "(p.codigo, p.nome, p.preco) " + " from "//
				+ Produto.class.getName() + " p ";
		if (likeName != null && likeName.length() > 0) {
			sql += " Where lower(p.nome) like :likeName ";
		}
		sql += " order by p.createDate desc ";
		//
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(sql);
		if (likeName != null && likeName.length() > 0) {
			query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
		}
		return new PaginationResult<ProdutoInfo>(query, page, maxResult, maxNavigationPage);
	}

	@Override
	public PaginationResult<ProdutoInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
		return queryProducts(page, maxResult, maxNavigationPage, null);
	}

}
