package br.com.java.projeto.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.java.projeto.domain.Funcionario;
import br.com.java.projeto.domain.Pessoa;

public class FuncionarioDAOTest {
	@Test
	@Ignore
	public void salvar() throws ParseException {
		Long codigoPessoa = 1L;
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigoPessoa);
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = new Funcionario();
		
		funcionario.setCarteiraTrabalho("NULO");
		funcionario.setDataAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse("06/06/2006"));
		funcionario.setPessoa(pessoa);
		
		funcionarioDAO.salvar(funcionario);
		
		System.out.println("Funcionario cadastrado com sucesso!");
	}
	
	@Test
	@Ignore
	public void listar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<Funcionario> resultado = funcionarioDAO.listar();
		
		System.out.println("Registros encontrados: " + resultado.size());
		
		for (Funcionario funcionario : resultado) {
			System.out.println("Codigo do Funcionario: " + funcionario.getCodigo());
			System.out.println("Carteira de Trabalho: " + funcionario.getCarteiraTrabalho());
			System.out.println("Data de admissão do Funcionario: " + funcionario.getDataAdmissao());
			System.out.println("Nome do Funcionario: " + funcionario.getPessoa().getNome());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		Long codigo = 2L;
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codigo);
		
		System.out.println("Codigo do Funcionario: " + funcionario.getCodigo());
		System.out.println("Carteira de Trabalho: " + funcionario.getCarteiraTrabalho());
		System.out.println("Data de admissão do Funcionario: " + funcionario.getDataAdmissao());
		System.out.println("Nome do Funcionario: " + funcionario.getPessoa().getNome());
	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 3L;
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codigo);
		
		System.out.println("Registro excluido:");
		System.out.println("Codigo do Funcionario: " + funcionario.getCodigo());
		System.out.println("Carteira de Trabalho: " + funcionario.getCarteiraTrabalho());
		System.out.println("Data de admissão do Funcionario: " + funcionario.getDataAdmissao());
		System.out.println("Nome do Funcionario: " + funcionario.getPessoa().getNome());
		
		funcionarioDAO.excluir(funcionario);
	}
	
	@Test
	@Ignore
	public void editar() throws ParseException {
		Long codigoPessoa = 2L;
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigoPessoa);
		
		Long codigoFuncionario = 2l;
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codigoFuncionario);
		
		System.out.println("Registro a ser editado:");
		System.out.println("Codigo do Funcionario: " + funcionario.getCodigo());
		System.out.println("Carteira de Trabalho: " + funcionario.getCarteiraTrabalho());
		System.out.println("Data de admissão do Funcionario: " + funcionario.getDataAdmissao());
		System.out.println("Nome do Funcionario: " + funcionario.getPessoa().getNome());
		
		funcionario.setCarteiraTrabalho("634.38051.79-1");
		funcionario.setDataAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse("09/07/2020"));
		funcionario.setPessoa(pessoa);
		
		funcionarioDAO.editar(funcionario);
		
		System.out.println("Registro editado:");
		System.out.println("Codigo do Funcionario: " + funcionario.getCodigo());
		System.out.println("Carteira de Trabalho: " + funcionario.getCarteiraTrabalho());
		System.out.println("Data de admissão do Funcionario: " + funcionario.getDataAdmissao());
		System.out.println("Nome do Funcionario: " + funcionario.getPessoa().getNome());
	}
}
