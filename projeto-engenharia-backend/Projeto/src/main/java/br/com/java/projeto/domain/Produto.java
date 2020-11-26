package br.com.java.projeto.domain;

import java.math.BigDecimal;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Produto extends GenericDomain {
	@Column(length = 80, nullable = false)
	private String descricao;

	@Column(nullable = false)
	private Short quantidade;

	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal preco;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Fabricante fabricante;

	//Armazena caminho do arquivo de upload temporario
	//@Transient - Informa que é um campo temporario
	@Transient
	private String caminho;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
}
