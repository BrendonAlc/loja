package br.com.brendonAlc.loja.modelo;

import javax.annotation.processing.Generated;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

	@EmbeddedId /*Mepeando da classe CategoriaId*/
	protected CategoriaId id;

	public Categoria() { // Constructor default de exigência do JPA
	}

	public Categoria(String nome) {
		this.id = new CategoriaId(nome, "xpto");
	}

	public String getNome() {
		return this.getNome();
	}

}
