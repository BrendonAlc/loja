package br.com.brendonAlc.loja;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.brendonAlc.loja.modelo.Categoria;
import br.com.brendonAlc.loja.modelo.Produto;

public class CategoriaDao {
	
	private EntityManager em;
	
	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar (Categoria categoria) {
		this.em.persist(categoria);
	}
	
}
