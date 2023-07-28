package br.com.brendonAlc.loja.modelo;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.crypto.Data;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.mapping.List;

import com.ibm.icu.math.BigDecimal;

@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "valor_total")
	private Double valorTotal;
	private LocalDate dataCadastro = LocalDate.now();

	@ManyToOne
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL) // Relacionando com a entidade ItemPedido um para muitos e indicando para o JPA
									// que existe um redirecionamento bidirecional, com tipo cascata.
	private java.util.List<ItemPedido> item = new ArrayList<>();

	public Pedido() { // Constructor default de exigência do JPA
	}

	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}

	public void adicionarItem(ItemPedido item) {
		item.setPedido(this); // utilizando cast para acessar o item da lista que foi pedido
		this.item.add(item); // utilizando cast para adicionar o item da lista
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
