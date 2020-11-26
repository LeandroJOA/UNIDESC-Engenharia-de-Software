package br.com.java.projeto.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.java.projeto.domain.ItemVenda;
import br.com.java.projeto.domain.Produto;
import br.com.java.projeto.domain.Venda;

public class ItemVendaDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Long codigoProduto = 1L;

		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigoProduto);

		Long codigoVenda = 4L;

		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(codigoVenda);

		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = new ItemVenda();

		itemVenda.setQuantidade(new Short("2"));
		itemVenda.setValorParcial(new BigDecimal("20"));
		itemVenda.setProduto(produto);
		itemVenda.setVenda(venda);

		itemVendaDAO.salvar(itemVenda);

		System.out.println("Registro adicionado com sucesso!");
	}

	@Test
	@Ignore
	public void listar() {
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		List<ItemVenda> resultado = itemVendaDAO.listar();

		System.out.println("Registros encontrados: " + resultado.size());

		for (ItemVenda itemVenda : resultado) {
			System.out.println("Codigo: " + itemVenda.getCodigo());
			System.out.println("Quantidade: " + itemVenda.getQuantidade());
			System.out.println("Valor Parcial: " + itemVenda.getValorParcial());
			System.out.println("Produto: " + itemVenda.getProduto().getDescricao());
			System.out.println("Codigo da venda : " + itemVenda.getVenda().getCodigo());
			System.out.println();
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 3L;

		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscar(codigo);

		System.out.println("Codigo: " + itemVenda.getCodigo());
		System.out.println("Quantidade: " + itemVenda.getQuantidade());
		System.out.println("Valor Parcial: " + itemVenda.getValorParcial());
		System.out.println("Produto: " + itemVenda.getProduto().getDescricao());
		System.out.println("Codigo da venda : " + itemVenda.getVenda().getCodigo());
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 3L;

		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscar(codigo);

		itemVendaDAO.excluir(itemVenda);

		System.out.println("Registro excluido");
		System.out.println("Codigo: " + itemVenda.getCodigo());
		System.out.println("Quantidade: " + itemVenda.getQuantidade());
		System.out.println("Valor Parcial: " + itemVenda.getValorParcial());
		System.out.println("Produto: " + itemVenda.getProduto().getDescricao());
		System.out.println("Codigo da venda : " + itemVenda.getVenda().getCodigo());
	}

	@Test
	@Ignore
	public void editar() {
		Long codigoProduto = 1L;

		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigoProduto);

		Long codigoVenda = 2L;

		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(codigoVenda);
		
		Long codigoItemVenda = 2L;
		
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscar(codigoItemVenda);
				
		System.out.println("Registro a ser editado:");
		System.out.println("Codigo: " + itemVenda.getCodigo());
		System.out.println("Quantidade: " + itemVenda.getQuantidade());
		System.out.println("Valor Parcial: " + itemVenda.getValorParcial());
		System.out.println("Produto: " + itemVenda.getProduto().getDescricao());
		System.out.println("Codigo da venda : " + itemVenda.getVenda().getCodigo());
		
		itemVenda.setQuantidade(new Short("1"));
		itemVenda.setValorParcial(new BigDecimal("13.7"));
		itemVenda.setProduto(produto);
		itemVenda.setVenda(venda);
		
		itemVendaDAO.editar(itemVenda);
		
		System.out.println("Registro editado:");
		System.out.println("Codigo: " + itemVenda.getCodigo());
		System.out.println("Quantidade: " + itemVenda.getQuantidade());
		System.out.println("Valor Parcial: " + itemVenda.getValorParcial());
		System.out.println("Produto: " + itemVenda.getProduto().getDescricao());
		System.out.println("Codigo da venda : " + itemVenda.getVenda().getCodigo());
	}
}
