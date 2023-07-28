package br.com.brendonAlc.loja.teste;

import javax.persistence.EntityManager;

import br.com.brendonAlc.loja.CategoriaDao;
import br.com.brendonAlc.loja.ClienteDao;
import br.com.brendonAlc.loja.PedidoDao;
import br.com.brendonAlc.loja.ProdutoDao;
import br.com.brendonAlc.loja.modelo.Categoria;
import br.com.brendonAlc.loja.modelo.Cliente;
import br.com.brendonAlc.loja.modelo.ItemPedido;
import br.com.brendonAlc.loja.modelo.Pedido;
import br.com.brendonAlc.loja.modelo.Produto;
import br.com.brendonAlc.util.JPAUtil;

public class CadastroDePedido {

	public static void main(String[] args) {
		PopularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		Produto produto = produtoDao.buscarPorId(1l);
		Cliente cliente = clienteDao.buscarPorId(1l);
		
		em.getTransaction().begin();
		
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		
		
		
		em.getTransaction().commit();
		
	}
	
	private static void PopularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		
		Produto celular = new Produto("Xiomi Redmi", "Produto muito legal.", new Double("800.0"), celulares);
		
		Cliente cliente = new Cliente("Pedro", "154654");
		
		//Instanciar criação do entityManager
		EntityManager em = JPAUtil.getEntityManager();
		
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin(); //Iniciando transação
		
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		
		
		em.getTransaction().commit();//Finalizando transação
		em.close();
	}
}
