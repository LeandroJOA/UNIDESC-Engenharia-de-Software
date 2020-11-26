package br.com.java.projeto.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.java.projeto.domain.Cliente;
import br.com.java.projeto.domain.Pessoa;

public class ClienteDAOTest {
	@Test
	@Ignore
	public void salvar() throws ParseException {
		Long codigoPessoa = 1L;
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigoPessoa);
		
		Cliente cliente = new Cliente();
		//cliente.setDataCadastro(new Date());	//Captura data atual
		cliente.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse("09/06/2015"));
		cliente.setLiberado(true);
		cliente.setPessoa(pessoa);
		
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(cliente);
		
		System.out.println("Cliente salvo com sucesso");
	}
	
	@Test
	@Ignore
	public void listar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> resultado = clienteDAO.listar();
		
		System.out.println("Registros encontrados: " + resultado.size());
		
		for (Cliente cliente : resultado) {
			System.out.println("Codigo do Cliente: " + cliente.getCodigo());
			System.out.println("Data de Cadastro: " + cliente.getDataCadastro());
			System.out.println("Status de Liberação: " + cliente.getLiberado());
			System.out.println("Nome do Cliente: " + cliente.getPessoa().getNome());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		Long codigo = 2L;
		
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigo);
		
		System.out.println("Codigo do Cliente: " + cliente.getCodigo());
		System.out.println("Data de Cadastro: " + cliente.getDataCadastro());
		System.out.println("Status de Liberação: " + cliente.getLiberado());
		System.out.println("Nome do Cliente: " + cliente.getPessoa().getNome());
	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 3L;
		
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigo);
		
		clienteDAO.excluir(cliente);
		
		System.out.println("Registro excluido:");
		System.out.println("Codigo do Cliente: " + cliente.getCodigo());
		System.out.println("Data de Cadastro: " + cliente.getDataCadastro());
		System.out.println("Status de Liberação: " + cliente.getLiberado());
		System.out.println("Nome do Cliente: " + cliente.getPessoa().getNome());
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigoPessoa = 2L;
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigoPessoa);
		
		Long codigoCliente = 2L;
		
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigoCliente);
		
		System.out.println("Registro a ser editado:");
		System.out.println("Codigo do Cliente: " + cliente.getCodigo());
		System.out.println("Data de Cadastro: " + cliente.getDataCadastro());
		System.out.println("Status de Liberação: " + cliente.getLiberado());
		System.out.println("Nome do Cliente: " + cliente.getPessoa().getNome());
		
		cliente.setDataCadastro(new Date());
		cliente.setLiberado(true);
		cliente.setPessoa(pessoa);
		
		clienteDAO.editar(cliente);
		
		System.out.println("Registro editado:");
		System.out.println("Codigo do Cliente: " + cliente.getCodigo());
		System.out.println("Data de Cadastro: " + cliente.getDataCadastro());
		System.out.println("Status de Liberação: " + cliente.getLiberado());
		System.out.println("Nome do Cliente: " + cliente.getPessoa().getNome());
	}
}
