package br.com.java.dao;

import br.com.java.entity.Produto;
import br.com.java.model.PaginationResult;
import br.com.java.model.ProdutoInfo;

public interface ProdutoDAO {
	
	public Produto descProduto(String codigo);

	public ProdutoInfo descProdutoInfo(String codigo);

	public PaginationResult<ProdutoInfo> queryProducts(int page, int maxResult, int maxNavigationPage);

	public PaginationResult<ProdutoInfo> queryProducts(int page, int maxResult, int maxNavigationPage, String likeName);

	public void salvar(ProdutoInfo produtoInfo);

}
