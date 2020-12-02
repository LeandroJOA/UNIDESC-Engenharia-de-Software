package br.com.java.projeto.domain;

import java.math.BigDecimal;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Produto extends GenericDomain {
	@Column(length = 200, nullable = false)
	private String descricao;

	@Column(nullable = false)
	private Short estoque;

	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal preco;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Fornecedor fornecedor;

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

	public Short getEstoque() {
		return estoque;
	}

	public void setEstoque(Short estoque) {
		this.estoque = estoque;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
}
