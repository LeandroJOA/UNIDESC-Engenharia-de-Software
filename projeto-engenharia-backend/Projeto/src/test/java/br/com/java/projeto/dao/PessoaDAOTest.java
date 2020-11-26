package br.com.java.projeto.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.java.projeto.domain.Cidade;
import br.com.java.projeto.domain.Pessoa;

public class PessoaDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Long codigoCidade = 1L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);

		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Layane");
		pessoa.setCpf("666.666.666.66");
		pessoa.setRg("6.666.666");
		pessoa.setRua("Florais do Planalto");
		pessoa.setNumero(new Short("0"));
		pessoa.setBairro("Alguma");
		pessoa.setCep("11111-777");
		pessoa.setComplemento("Condominio");
		pessoa.setTelefone("Não existente");
		pessoa.setCelular("6611111111");
		pessoa.setEmail("666@gmail.com");
		pessoa.setCidade(cidade);

		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.salvar(pessoa);
		System.out.println("Informações salvas com sucesso!");
	}

	@Test
	@Ignore
	public void listar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		List<Pessoa> resultado = pessoaDAO.listar();

		System.out.println("Registros encontrados: " + resultado.size());

		for (Pessoa pessoa : resultado) {
			System.out.println("Codigo: " + pessoa.getCodigo());
			System.out.println("Nome: " + pessoa.getNome());
			System.out.println("CPF: " + pessoa.getCpf());
			System.out.println("RG: " + pessoa.getRg());
			System.out.println("Rua: " + pessoa.getRua());
			System.out.println("Numero: " + pessoa.getNumero());
			System.out.println("Bairro: " + pessoa.getBairro());
			System.out.println("CEP: " + pessoa.getCep());
			System.out.println("Complemento: " + pessoa.getComplemento());
			System.out.println("Telefone: " + pessoa.getTelefone());
			System.out.println("Celular: " + pessoa.getCelular());
			System.out.println("Email: " + pessoa.getEmail());
			System.out.println("Cidade: " + pessoa.getCidade().getNome());
			System.out.println();
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 2L;

		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigo);

		System.out.println("Codigo: " + pessoa.getCodigo());
		System.out.println("Nome: " + pessoa.getNome());
		System.out.println("CPF: " + pessoa.getCpf());
		System.out.println("RG: " + pessoa.getRg());
		System.out.println("Rua: " + pessoa.getRua());
		System.out.println("Numero: " + pessoa.getNumero());
		System.out.println("Bairro: " + pessoa.getBairro());
		System.out.println("CEP: " + pessoa.getCep());
		System.out.println("Complemento: " + pessoa.getComplemento());
		System.out.println("Telefone: " + pessoa.getTelefone());
		System.out.println("Celular: " + pessoa.getCelular());
		System.out.println("Email: " + pessoa.getEmail());
		System.out.println("Cidade: " + pessoa.getCidade().getNome());
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 3L;

		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigo);

		System.out.println("Registros a serem excluidos");
		System.out.println("Codigo: " + pessoa.getCodigo());
		System.out.println("Nome: " + pessoa.getNome());
		System.out.println("CPF: " + pessoa.getCpf());
		System.out.println("RG: " + pessoa.getRg());
		System.out.println("Rua: " + pessoa.getRua());
		System.out.println("Numero: " + pessoa.getNumero());
		System.out.println("Bairro: " + pessoa.getBairro());
		System.out.println("CEP: " + pessoa.getCep());
		System.out.println("Complemento: " + pessoa.getComplemento());
		System.out.println("Telefone: " + pessoa.getTelefone());
		System.out.println("Celular: " + pessoa.getCelular());
		System.out.println("Email: " + pessoa.getEmail());
		System.out.println("Cidade: " + pessoa.getCidade().getNome());

		pessoaDAO.excluir(pessoa);

		System.out.println("Registro excluido com sucesso!");
	}

	@Test
	@Ignore
	public void editar() {
		Long codigoCidade = 6L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);

		System.out.println("Cidade: " + cidade.getNome());

		Long codigoPessoa = 2L;

		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigoPessoa);

		System.out.println("Registros a serem editados:");
		System.out.println("Codigo: " + pessoa.getCodigo());
		System.out.println("Nome: " + pessoa.getNome());
		System.out.println("CPF: " + pessoa.getCpf());
		System.out.println("RG: " + pessoa.getRg());
		System.out.println("Rua: " + pessoa.getRua());
		System.out.println("Numero: " + pessoa.getNumero());
		System.out.println("Bairro: " + pessoa.getBairro());
		System.out.println("CEP: " + pessoa.getCep());
		System.out.println("Complemento: " + pessoa.getComplemento());
		System.out.println("Telefone: " + pessoa.getTelefone());
		System.out.println("Celular: " + pessoa.getCelular());
		System.out.println("Email: " + pessoa.getEmail());
		System.out.println("Cidade: " + pessoa.getCidade().getNome());

		pessoa.setNome("Bruno");
		pessoa.setCpf("NULO");
		pessoa.setRg("NULO");
		pessoa.setRua("ALguma de Blumenau");
		pessoa.setNumero(new Short("24"));
		pessoa.setBairro("Algum de Blumenau");
		pessoa.setCep("NULO");
		pessoa.setComplemento("Casa");
		pessoa.setTelefone("NULO");
		pessoa.setCelular("47991117781");
		pessoa.setEmail("bruno@gmail.com");
		pessoa.setCidade(cidade);

		pessoaDAO.editar(pessoa);

		System.out.println("Registros editados:");
		System.out.println("Codigo: " + pessoa.getCodigo());
		System.out.println("Nome: " + pessoa.getNome());
		System.out.println("CPF: " + pessoa.getCpf());
		System.out.println("RG: " + pessoa.getRg());
		System.out.println("Rua: " + pessoa.getRua());
		System.out.println("Numero: " + pessoa.getNumero());
		System.out.println("Bairro: " + pessoa.getBairro());
		System.out.println("CEP: " + pessoa.getCep());
		System.out.println("Complemento: " + pessoa.getComplemento());
		System.out.println("Telefone: " + pessoa.getTelefone());
		System.out.println("Celular: " + pessoa.getCelular());
		System.out.println("Email: " + pessoa.getEmail());
		System.out.println("Cidade: " + pessoa.getCidade().getNome());

	}
}
