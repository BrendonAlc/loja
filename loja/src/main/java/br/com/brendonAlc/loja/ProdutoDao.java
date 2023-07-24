package br.com.brendonAlc.loja;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.brendonAlc.loja.modelo.Produto;

public class ProdutoDao {
	
	private EntityManager em;
	
	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar (Produto produto) {
		this.em.persist(produto);
	}
	
}
