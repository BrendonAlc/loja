package br.com.brendonAlc.loja.modelo;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
	
	public Categoria() { //Constructor default de exigência do JPA
	}

	public Categoria(String nome) {
		super();
		this.nome = nome;
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
