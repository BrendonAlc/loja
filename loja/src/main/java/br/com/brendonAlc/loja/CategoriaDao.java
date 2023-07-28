package br.com.brendonAlc.loja;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.brendonAlc.loja.modelo.Categoria;
import br.com.brendonAlc.loja.modelo.Cliente;
import br.com.brendonAlc.loja.modelo.Produto;

public class CategoriaDao {
	
	private EntityManager em;
	
	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar (Categoria categoria) {
		this.em.persist(categoria);
	}
	
	public void atualizar(Categoria categoria) {
		this.em.persist(categoria);
	}
	
	public void remover(Categoria categoria) {
		categoria = em.merge(categoria);
		this.em.persist(categoria);
	}
	
	public java.util.List<Categoria> buscarTodos() { //Lista de produtos com método buscarTodos
		String jpql = "SELECT p FROM Categoria p"; //Select utilizando o nome da entydade e não da tabela
		return em.createQuery(jpql, Categoria.class).getResultList();
	}
	
	public java.util.List<Categoria> buscarPorNome(String nome) { //Lista de produtos com método buscarTodos
		String jpql = "SELECT p FROM Categoria p WHERE p.nome = :nome "; //Select utilizando o nome da entydade e não da tabela
		return em.createQuery(jpql, Categoria.class).setParameter("nome", nome).getResultList();
	}
	
}
