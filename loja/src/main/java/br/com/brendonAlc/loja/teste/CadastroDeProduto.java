package br.com.brendonAlc.loja.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ibm.icu.math.BigDecimal;

import br.com.brendonAlc.loja.modelo.Produto;

public class CadastroDeProduto {

	public static void main(String[] args) {
		
		Produto celular = new Produto();
		
		celular.setNome("Xiomi Redmi");
		celular.setDescricao("Muito bom!");
		celular.setPreco(new BigDecimal("800"));
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
		EntityManager em = factory.createEntityManager();
		em.persist(celular);

	}
}
