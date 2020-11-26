package br.com.java.projeto.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.java.projeto.domain.Fabricante;
import br.com.java.projeto.domain.Produto;

public class ProdutoDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Long codigoFabricante = 5L;

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigoFabricante);

		Produto produto = new Produto();
		produto.setDescricao("Descrição generica de um remegio");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("69.420"));
		produto.setQuantidade(new Short("1"));

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);

		System.out.println("Produto salvo com sucesso!");
	}

	@Test
	@Ignore
	public void listar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> resultado = produtoDAO.listar();

		System.out.println("Registros encontrados: " + resultado.size());

		for (Produto produto : resultado) {
			System.out.println("Codigo do Produto: " + produto.getCodigo());
			System.out.println("Descrição do Produto: " + produto.getDescricao());
			System.out.println("Preço do Produto: R$" + produto.getPreco());
			System.out.println("Quantidade do Produto: " + produto.getQuantidade());
			System.out.println("Codigo da Fabricante: " + produto.getFabricante().getCodigo());
			System.out.println("Descrição da Fabricante: " + produto.getFabricante().getDescricao());
			System.out.println();
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 2L;

		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
		
		System.out.println("Codigo do Produto: " + produto.getCodigo());
		System.out.println("Descrição do Produto: " + produto.getDescricao());
		System.out.println("Preço do Produto: R$" + produto.getPreco());
		System.out.println("Quantidade do Produto: " + produto.getQuantidade());
		System.out.println("Codigo da Fabricante: " + produto.getFabricante().getCodigo());
		System.out.println("Descrição da Fabricante: " + produto.getFabricante().getDescricao());
	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 3L;

		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
		
		produtoDAO.excluir(produto);
		
		System.out.println("Produto excluido: ");
		System.out.println("Codigo do Produto: " + produto.getCodigo());
		System.out.println("Descrição do Produto: " + produto.getDescricao());
		System.out.println("Preço do Produto: R$" + produto.getPreco());
		System.out.println("Quantidade do Produto: " + produto.getQuantidade());
		System.out.println("Codigo da Fabricante: " + produto.getFabricante().getCodigo());
		System.out.println("Descrição da Fabricante: " + produto.getFabricante().getDescricao());
	}
	
	@Test
	
	public void editar() {
		Long codigoFabricante = 2L;
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigoFabricante);
		
		System.out.println("Codigo da Fabricante: " + fabricante.getCodigo());
		System.out.println("Descrição da Fabricante: " + fabricante.getDescricao());
		
		Long codigoProduto = 4L;
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigoProduto);
		
		System.out.println("Produto a ser editado: ");
		System.out.println("Codigo do Produto: " + produto.getCodigo());
		System.out.println("Descrição do Produto: " + produto.getDescricao());
		System.out.println("Preço do Produto: R$" + produto.getPreco());
		System.out.println("Quantidade do Produto: " + produto.getQuantidade());
		System.out.println("Codigo da Fabricante: " + produto.getFabricante().getCodigo());
		System.out.println("Descrição da Fabricante: " + produto.getFabricante().getDescricao());
		
		produto.setDescricao("Rivotril® é indicado para tratar crises epilépticas e espasmos infantis");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("1.50"));
		produto.setQuantidade(new Short("50"));
		
		produtoDAO.editar(produto);
		
		System.out.println("Produto editado: ");
		System.out.println("Codigo do Produto: " + produto.getCodigo());
		System.out.println("Descrição do Produto: " + produto.getDescricao());
		System.out.println("Preço do Produto: R$" + produto.getPreco());
		System.out.println("Quantidade do Produto: " + produto.getQuantidade());
		System.out.println("Codigo da Fabricante: " + produto.getFabricante().getCodigo());
		System.out.println("Descrição da Fabricante: " + produto.getFabricante().getDescricao());
	}
}
