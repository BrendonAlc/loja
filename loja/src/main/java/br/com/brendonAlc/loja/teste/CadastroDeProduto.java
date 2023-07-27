package br.com.brendonAlc.loja.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.mapping.List;

import com.ibm.icu.math.BigDecimal;

import br.com.brendonAlc.loja.CategoriaDao;
import br.com.brendonAlc.loja.ProdutoDao;
import br.com.brendonAlc.loja.modelo.Categoria;
import br.com.brendonAlc.loja.modelo.Produto;
import br.com.brendonAlc.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		
		cadastrarProdutos();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);

		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());

		java.util.List<Produto> todos = produtoDao.buscarTodos();
		todos.forEach(p2 -> System.out.println(p.getNome()));
		
		Double precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiomi Redmi");
		System.out.println("Preco do produto" + precoDoProduto);
		
		
	}

	private static void cadastrarProdutos() {
		Categoria celulares = new Categoria("CELULARES");
		
		Produto celular = new Produto("Xiomi Redmi", "Produto muito legal.", new Double("800.0"), celulares);
		
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
