package br.com.brendonAlc.loja.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ibm.icu.math.BigDecimal;

import br.com.brendonAlc.loja.ProdutoDao;
import br.com.brendonAlc.loja.modelo.Categoria;
import br.com.brendonAlc.loja.modelo.Produto;
import br.com.brendonAlc.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		
		Produto celular = new Produto("Xiomi Redmi", "Produto muito legal.", new BigDecimal("800"), Categoria.CELULARES);
		
		//Instanciar criação do entityManager
		EntityManager em = JPAUtil.getEntityManager();
		
		
		ProdutoDao dao = new ProdutoDao(em);
		
		em.getTransaction().begin(); //Iniciando transação
		dao.cadastrar(celular);
		em.getTransaction().commit();//Finalizando transação
		em.close();

	}
}
