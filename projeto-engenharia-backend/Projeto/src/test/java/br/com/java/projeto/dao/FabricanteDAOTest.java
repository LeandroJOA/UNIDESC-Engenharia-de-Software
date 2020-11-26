package br.com.java.projeto.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.java.projeto.domain.Fabricante;

public class FabricanteDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Qualquer coisa");

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.salvar(fabricante);
	}

	@Test
	@Ignore
	public void listar() {
		FabricanteDAO FabricanteDAO = new FabricanteDAO();
		List<Fabricante> resultado = FabricanteDAO.listar();

		System.out.println("Total de Registros Encontrados: " + resultado.size());

		for (Fabricante fabricante : resultado) {
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		Long codigo = 5L;

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);

		if (fabricante == null) {
			System.out.println("Nenhum registro encontrado!");
		} else {
			System.out.println("Registro encontrado:");
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		}

	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 4L;

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);

		if (fabricante == null) {
			System.out.println("Nenhum registro encontrado!");
		} else {
			fabricanteDAO.excluir(fabricante);
			System.out.println("Registro deletado:");
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long codigo = 5L;

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);
		
		if (fabricante == null) {
			System.out.println("Registro não encontrado!");
		} else {
			System.out.println("Registro alterado de:");
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
			
			fabricante.setDescricao("Pode deixar ai C:");
			fabricanteDAO.editar(fabricante);
			
			System.out.println("Para:");
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		}
	}

	@Test

	public void merge() {
		/*
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Fabricante A");

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.salvar(fabricante);
		 */
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(1L);

		fabricante.setDescricao("Fabricante B");

		fabricanteDAO.merge(fabricante);
	}
}
