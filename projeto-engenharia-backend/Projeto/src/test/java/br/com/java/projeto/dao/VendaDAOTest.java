package br.com.java.projeto.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.java.projeto.domain.Cliente;
import br.com.java.projeto.domain.Funcionario;
import br.com.java.projeto.domain.Venda;

public class VendaDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Long codigoCliente = 1L;
		
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigoCliente);
		
		Long codigoFuncionario = 1L;
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codigoFuncionario);
		
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = new Venda();
		
		venda.setHorario(new Date());
		venda.setPrecoTotal(new BigDecimal("69.420"));
		venda.setCliente(cliente);
		venda.setFuncionario(funcionario);
		
		vendaDAO.salvar(venda);
		
		System.out.println("Venda cadastrada com sucesso!");
	}
	
	@Test
	@Ignore
	public void listar() {
		VendaDAO vendaDAO = new VendaDAO();
		List<Venda> resultado = vendaDAO.listar();
		
		System.out.println("Registros encontrados: " + resultado.size());
		
		for (Venda venda : resultado) {
			System.out.println("Codigo da Venda: " + venda.getCodigo());
			System.out.println("Horario da Venda: " + venda.getHorario());
			System.out.println("Valor totoal da Venda: " + venda.getPrecoTotal());
			System.out.println("Cliente: " + venda.getCliente().getPessoa().getNome());
			System.out.println("Funcionario: " + venda.getFuncionario().getPessoa().getNome());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		Long codigo = 2L;
		
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(codigo);
		
		System.out.println("Codigo da Venda: " + venda.getCodigo());
		System.out.println("Horario da Venda: " + venda.getHorario());
		System.out.println("Valor totoal da Venda: " + venda.getPrecoTotal());
		System.out.println("Cliente: " + venda.getCliente().getPessoa().getNome());
		System.out.println("Funcionario: " + venda.getFuncionario().getPessoa().getNome());
	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 3L;
		
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(codigo);
		
		vendaDAO.excluir(venda);
		
		System.out.println("Registro excluido:");
		System.out.println("Codigo da Venda: " + venda.getCodigo());
		System.out.println("Horario da Venda: " + venda.getHorario());
		System.out.println("Valor totoal da Venda: " + venda.getPrecoTotal());
		System.out.println("Cliente: " + venda.getCliente().getPessoa().getNome());
		System.out.println("Funcionario: " + venda.getFuncionario().getPessoa().getNome());
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigoCliente = 2L;
		
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigoCliente);
		
		Long codigoFuncionario = 1L;
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codigoFuncionario);
		
		Long codigoVenda = 4L;
		
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(codigoVenda);
		
		System.out.println("Registro a ser edtiado:");
		System.out.println("Codigo da Venda: " + venda.getCodigo());
		System.out.println("Horario da Venda: " + venda.getHorario());
		System.out.println("Valor totoal da Venda: " + venda.getPrecoTotal());
		System.out.println("Cliente: " + venda.getCliente().getPessoa().getNome());
		System.out.println("Funcionario: " + venda.getFuncionario().getPessoa().getNome());
		
		venda.setHorario(new Date());
		venda.setPrecoTotal(new BigDecimal("50.00"));
		venda.setCliente(cliente);
		venda.setFuncionario(funcionario);
		
		vendaDAO.editar(venda);
		
		System.out.println("Registro edtiado:");
		System.out.println("Codigo da Venda: " + venda.getCodigo());
		System.out.println("Horario da Venda: " + venda.getHorario());
		System.out.println("Valor totoal da Venda: " + venda.getPrecoTotal());
		System.out.println("Cliente: " + venda.getCliente().getPessoa().getNome());
		System.out.println("Funcionario: " + venda.getFuncionario().getPessoa().getNome());
	}
}
