package br.com.brendonAlc.loja.modelo;

import javax.persistence.Entity;

@Entity
public class Livro extends Produto {

	protected String autor;
	protected Integer numeroDePaginas;

	protected Livro() {
	}

	protected Livro(String autor, Integer numeroDePaginas) {
		this.autor = autor;
		this.numeroDePaginas = numeroDePaginas;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public void setNumeroDePaginas(Integer numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}

}
