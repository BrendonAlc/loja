package br.com.brendonAlc.loja;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.mapping.List;
import org.hibernate.query.Query;

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
	
	/*Realizar select para que ignore o parametro que estiver nulo com JPQL*/
	public java.util.List<Produto> buscaPorParametros(String nome, Double preco, LocalDate dataCadastro){
		String jpql = "SELECT p FROM Produto p WHERE 1=1";
		if (nome != null && !nome.trim().isEmpty()) {
			jpql = "AND p.nome = :nome"; 
		}
		if (preco != null ) {
			jpql = "AND p.preco = :preco";
		}
		if (dataCadastro != null ) {
			jpql = "AND p.dataCadastro = :dataCadastro";
		}
		
		//Variavel para setar o formato da query
		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		if (nome != null && !nome.trim().isEmpty()) {
			query.setParameter("nome", nome); 
		}
		if (preco != null ) {
			query.setParameter("preco", preco);
		}
		if (dataCadastro != null ) {
			query.setParameter("dataCadastro", dataCadastro);
		}
		
		return query.getResultList();
	}
	
	
	/*Realizar select para que ignore o parametro que estiver nulo com Criteria API
	public java.util.List<Produto> buscaPorParametrosComCriteria(String nome, Double preco, LocalDate dataCadastro){
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);
		
		Predicate filtros = builder.and();
		if (nome != null && !nome.trim().isEmpty()) {
			filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
		}
		
		if (preco != null ) {
			filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
		}
		
		if (dataCadastro != null ) {
			filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));
		}
		query.where(filtros);
		
		return em.createQuery(query).getResultList();
	}*/
	
}
