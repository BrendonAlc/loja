package br.com.brendonAlc.loja;

import javax.persistence.EntityManager;

import org.hibernate.mapping.List;

import com.ibm.icu.math.BigDecimal;

import br.com.brendonAlc.loja.modelo.Pedido;
import br.com.brendonAlc.loja.vo.RelatorioDeVendasVo;

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
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}
	
	//relatório com 3 objetos, sendo produto, quantidade e nome, ordenando por order crescente a quantidade
	public java.util.List<RelatorioDeVendasVo> relatorioDeVendas() {
		String jpql = "SELECT new br.com.brendonAlc.loja.vo.RelatorioDeVendasVo( produto.nome, SUM(item.quantidade) as quantidadeTotal, " 
				+ "MAX(pedido.data)) FROM Pedido pedido "
				+ "JOIN pedido.itens item JOIN item.produto produto GROUP BY produto.nome ORDER BY quantidadeTotal DESC";
		return em.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
	}
	
	//Query para busca caso o EntityManager estiver fechado e realizar a busca sendo eager e vir junto com a entidade principal
	public Pedido buscarPedidoComCliente(Long id) {
		return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class).setParameter("id", id).getSingleResult();
	}
}
