package br.com.brendonAlc.loja;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.mapping.List;

import br.com.brendonAlc.loja.modelo.Categoria;
import br.com.brendonAlc.loja.modelo.Produto;

public class ProdutoDao {
	
	private EntityManager em;
	
	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar (Produto produto) {
		this.em.persist(produto);
	}
	
	public void atualizar(Categoria categoria) {
		this.em.persist(categoria);
	}
	
	public void remover(Categoria categoria) {
		categoria = em.merge(categoria);
		this.em.persist(categoria);
	}
	
	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
	}
	
	public java.util.List<Produto> buscarTodos() { //Lista de produtos com método buscarTodos
		String jpql = "SELECT p FROM Produto p"; //Select utilizando o nome da entydade e não da tabela
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public java.util.List<Produto> buscarPorNome(String nome) { //Lista de produtos com método buscarTodos
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome "; //Select utilizando o nome da entydade e não da tabela
		return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
	}
	
	public java.util.List<Produto> buscarPorCategoria(String nome) { //Lista de produtos com método buscarTodos
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome "; //Select utilizando o nome da entydade e não da tabela
		return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
	}
	
	//Buscar somente pelo preco com um único resultado de busca
	public Double buscarPrecoDoProdutoComNome(String nome) { //Lista de produtos com método buscarTodos
		String jpql = "SELECT p.preco FROM Produto p WHERE p.categoria.nome = :nome "; //Select utilizando o nome da entydade e não da tabela
		return em.createQuery(jpql, Double.class).setParameter("nome", nome).getSingleResult();
	}
}
