package br.com.brendonAlc.loja;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.mapping.List;

import br.com.brendonAlc.loja.modelo.Categoria;
import br.com.brendonAlc.loja.modelo.Cliente;
import br.com.brendonAlc.loja.modelo.Pedido;
import br.com.brendonAlc.loja.modelo.Produto;

public class ClienteDao {
	
	private EntityManager em;
	
	public ClienteDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar (Cliente cliente) {
		this.em.persist(cliente);
	}
	
	public void atualizar(Cliente cliente) {
		this.em.persist(cliente);
	}
	
	public void remover(Cliente cliente) {
		cliente = em.merge(cliente);
		this.em.persist(cliente);
	}
	
	public Cliente buscarPorId(Long id) {
		return em.find(Cliente.class, id);
	}
	
	public java.util.List<Cliente> buscarTodos() { //Lista de produtos com método buscarTodos
		String jpql = "SELECT p FROM Cliente p"; //Select utilizando o nome da entydade e não da tabela
		return em.createQuery(jpql, Cliente.class).getResultList();
	}
	
	public java.util.List<Cliente> buscarPorNome(String nome) { //Lista de produtos com método buscarTodos
		String jpql = "SELECT p FROM Cliente p WHERE p.nome = :nome "; //Select utilizando o nome da entydade e não da tabela
		return em.createQuery(jpql, Cliente.class).setParameter("nome", nome).getResultList();
	}
	
}
