package br.com.java.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import br.com.java.entity.Pedido;
import br.com.java.entity.PedidoDetalhe;
import br.com.java.entity.Produto;
import br.com.java.model.CarrinhoInfo;
import br.com.java.model.CarrinhoLinhaInfo;
import br.com.java.model.ClienteInfo;
import br.com.java.model.PaginationResult;
import br.com.java.model.PedidoDetalheInfo;
import br.com.java.model.PedidoInfo;
import br.com.java.model.ProdutoInfo;

@Transactional
public class PedidoDAOImpl implements PedidoDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ProdutoDAO produtoDAO;
	
	private int getMaxOrderNum() {
		String sql = "Select max(o.pedidoNum) from " + Pedido.class.getName() + " o ";
//		Session session = sessionFactory.getCurrentSession();
		Session session;// = sessionFactory.getCurrentSession();

		try {
			// Step-2: Implementation
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			// Step-3: Implementation
			session = sessionFactory.openSession();
		}
		Query query = session.createQuery(sql);
		Integer value = (Integer) query.uniqueResult();
		if (value == null) {
			return 0;
		}
		return value;
	}

	@Override
	public void salvarPedido(CarrinhoInfo carrinhoInfo) {
		// TODO Auto-generated method stub
//	        Session session = sessionFactory.getCurrentSession();
		Session session;// = sessionFactory.getCurrentSession();

		try {
			// Step-2: Implementation
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			// Step-3: Implementation
			session = sessionFactory.openSession();
		}

		Transaction transaction = null;
	     
	        int pedidoNum = this.getMaxOrderNum() + 1;
	        Pedido pedido = new Pedido();
	 
	        pedido.setId(UUID.randomUUID().toString());
	        pedido.setPedidoNum(pedidoNum);
	        pedido.setPedidoData(new Date());
	        pedido.setMontante(carrinhoInfo.getMontanteTotal());
	 
	        ClienteInfo clienteInfo = carrinhoInfo.getClienteInfo();
	        pedido.setClienteNome(clienteInfo.getNome());
	        pedido.setClienteEmail(clienteInfo.getEmail());
	        pedido.setClienteTelefone(clienteInfo.getTelefone());
	        pedido.setClienteEndereco(clienteInfo.getEndereco());
	        
	      
			
			try {
				transaction = session.beginTransaction();
				session.save(pedido);
				transaction.commit();
				} catch (HibernateException e) {
				transaction.rollback();
				e.printStackTrace();
				} finally {
//				session.close();
		
		
				}
	 
//	        session.persist(pedido);
	 
	        List<CarrinhoLinhaInfo> linhas = carrinhoInfo.getCarrinhoLinhas();
	 
	        for (CarrinhoLinhaInfo linha : linhas) {
	            PedidoDetalhe detalhe = new PedidoDetalhe();
	            detalhe.setId(UUID.randomUUID().toString());
	            detalhe.setPedido(pedido);
	            detalhe.setMontante(linha.getMontante());
	            detalhe.setPreco(linha.getProdutoInfo().getPreco());
	            detalhe.setQuantidade(linha.getQuantidade());
	 
	            String codigo = linha.getProdutoInfo().getCodigo();
	            Produto produto = this.produtoDAO.descProduto(codigo);
	            detalhe.setProduto(produto);
	            
	        	try {
					transaction = session.beginTransaction();
					session.save(detalhe);
					transaction.commit();
					} catch (HibernateException e) {
					transaction.rollback();
					e.printStackTrace();
					} finally {
					
			
			
					}
	 
//	            session.persist(detalhe);
	            
	        }
	        carrinhoInfo.setPedidoNum(pedidoNum);
	        session.close();
		
	}

	@Override
	public PaginationResult<PedidoInfo> listaPedidoInfo(int page, int maxResult, int maxNavigationPage) {
		// TODO Auto-generated method stub
		String sql = "Select new " + PedidoInfo.class.getName()//
				+ "(pedido.id, pedido.pedidoData, pedido.pedidoNum, pedido.montante, "
				+ " pedido.clienteNome, pedido.clienteEndereco, pedido.clienteEmail, pedido.clienteTelefone) " + " from "
				+ Pedido.class.getName() + " pedido "//
				+ " order by pedido.pedidoNum desc";
//		Session session = this.sessionFactory.getCurrentSession();
		
		Session session;

		try {
			// Step-2: Implementation
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			// Step-3: Implementation
			session = sessionFactory.openSession();
		}
		Query query = session.createQuery(sql);
		return new PaginationResult<PedidoInfo>(query, page, maxResult, maxNavigationPage);
	}
	
	public Pedido descPedido(String pedidoId) {
//		Session session = sessionFactory.getCurrentSession();
		Session session;

		try {
			// Step-2: Implementation
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			// Step-3: Implementation
			session = sessionFactory.openSession();
		}
		Criteria crit = session.createCriteria(Pedido.class);
		crit.add(Restrictions.eq("id", pedidoId));
		return (Pedido) crit.uniqueResult();
	}

	@Override
	public PedidoInfo getPedidoInfo(String pedidoId) {
		// TODO Auto-generated method stub
		Pedido pedido = this.descPedido(pedidoId);
		if (pedido == null) {
			return null;
		}
		return new PedidoInfo(pedido.getId(), pedido.getPedidoData(),//
				pedido.getPedidoNum(), pedido.getMontante(), pedido.getClienteNome(),//
				pedido.getClienteEndereco(), pedido.getClienteEmail(), pedido.getClienteTelefone());
	}

	@Override
	public List<PedidoDetalheInfo> listarInformacoesDetalhesPedido(String pedidoId) {
		// TODO Auto-generated method stub
		String sql = "Select new " + PedidoDetalheInfo.class.getName() //
				+ "(d.id, d.produto.codigo, d.produto.nome , d.quantidade,d.preco,d.montante) "//
				+ " from " + PedidoDetalhe.class.getName() + " d "//
				+ " where d.pedido.id = :pedidoId ";
		
//		Session session = this.sessionFactory.getCurrentSession();
		Session session;

		try {
			// Step-2: Implementation
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			// Step-3: Implementation
			session = sessionFactory.openSession();
		}
		
		Query query = session.createQuery(sql);
        query.setParameter("pedidoId", pedidoId);
		return query.list();
	}

}
