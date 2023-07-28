package br.com.brendonAlc.loja;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.mapping.List;

import br.com.brendonAlc.loja.modelo.Categoria;
import br.com.brendonAlc.loja.modelo.Pedido;
import br.com.brendonAlc.loja.modelo.Produto;

public class PedidoDao {
	
	private EntityManager em;
	
	public PedidoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar (Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public void atualizar(Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public void remover(Pedido pedido) {
		pedido = em.merge(pedido);
		this.em.persist(pedido);
	}
	
	public Pedido buscarPorId(Long id) {
		return em.find(Pedido.class, id);
	}
	
	public java.util.List<Pedido> buscarTodos() { //Lista de produtos com método buscarTodos
		String jpql = "SELECT p FROM Produto p"; //Select utilizando o nome da entydade e não da tabela
		return em.createQuery(jpql, Pedido.class).getResultList();
	}
	
	public java.util.List<Pedido> buscarPorNome(String nome) { //Lista de produtos com método buscarTodos
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome "; //Select utilizando o nome da entydade e não da tabela
		return em.createQuery(jpql, Pedido.class).setParameter("nome", nome).getResultList();
	}
	
	public java.util.List<Pedido> buscarPorCategoria(String nome) { //Lista de produtos com método buscarTodos
		String jpql = "SELECT p FROM Pedido p WHERE p.categoria.nome = :nome "; //Select utilizando o nome da entydade e não da tabela
		return em.createQuery(jpql, Pedido.class).setParameter("nome", nome).getResultList();
	}
	
}
