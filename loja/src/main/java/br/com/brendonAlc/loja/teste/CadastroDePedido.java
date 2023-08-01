package br.com.brendonAlc.loja.teste;

import java.util.Iterator;

import javax.persistence.EntityManager;

import com.ibm.icu.math.BigDecimal;

import br.com.brendonAlc.loja.CategoriaDao;
import br.com.brendonAlc.loja.ClienteDao;
import br.com.brendonAlc.loja.PedidoDao;
import br.com.brendonAlc.loja.ProdutoDao;
import br.com.brendonAlc.loja.modelo.Categoria;
import br.com.brendonAlc.loja.modelo.Cliente;
import br.com.brendonAlc.loja.modelo.ItemPedido;
import br.com.brendonAlc.loja.modelo.Pedido;
import br.com.brendonAlc.loja.modelo.Produto;
import br.com.brendonAlc.loja.vo.RelatorioDeVendasVo;
import br.com.brendonAlc.util.JPAUtil;

public class CadastroDePedido {

	public static void main(String[] args) {
		PopularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		PedidoDao pedidoDao = new PedidoDao(em);
		Pedido pedido = pedidoDao.buscarPedidoComCliente(1l);
		em.close();
		System.out.println(pedido.getCliente().getDadosPessoais().getNome());// Acessando informcao do cliente com
																				// entityManager fechado, utilizando
																				// método fetch criado em PedidoDao

		// System.out.println(pedido.getItem().size());//imprimindo quantos itens tem no
		// pedido

		/*
		 * Testando busca por ID, criando novos objetos, adicionando item ao pedido por
		 * meio de relacao com entidade produto, utilizando classe de
		 * RelatorioDeVendasVo para saber o valorVendido
		 */
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);

		Produto produto = produtoDao.buscarPorId(1l);
		Produto produto2 = produtoDao.buscarPorId(2l);
		Produto produto3 = produtoDao.buscarPorId(3l);

		Cliente cliente = clienteDao.buscarPorId(1l);

		em.getTransaction().begin();

		// Cadastrando os pedidos
		Pedido pedido1 = new Pedido(cliente);
		pedido1.adicionarItem(new ItemPedido(10, pedido1, produto));
		pedido1.adicionarItem(new ItemPedido(20, pedido1, produto2));

		Pedido pedido2 = new Pedido(cliente);
		pedido1.adicionarItem(new ItemPedido(2, pedido1, produto3));

		PedidoDao pedidoDao1 = new PedidoDao(em);
		pedidoDao1.cadastrar(pedido1);
		pedidoDao1.cadastrar(pedido2);

		em.getTransaction().commit();

		BigDecimal totalVendido = pedidoDao1.valorTotalVendido();
		System.out.println("Valor Total: " + totalVendido);

		java.util.List<RelatorioDeVendasVo> relatorio = pedidoDao1.relatorioDeVendas();
		relatorio.forEach(System.out::println);

	}

	private static void PopularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");

		Produto celular = new Produto("Xiomi Redmi", "Produto muito legal.", new Double("800.0"), celulares);
		Produto videogame1 = new Produto("PS5", "Playstation 5", new Double("1000.0"), videogames);
		Produto macbook = new Produto("Macbook", "Macbook pro.", new Double("1500.0"), informatica);

		Cliente cliente = new Cliente("Pedro", "154654");

		// Instanciar criação do entityManager
		EntityManager em = JPAUtil.getEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);

		em.getTransaction().begin(); // Iniciando transação

		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(videogames);
		categoriaDao.cadastrar(informatica);

		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(videogame1);
		produtoDao.cadastrar(macbook);

		em.getTransaction().commit();// Finalizando transação
		em.close();
	}
}
