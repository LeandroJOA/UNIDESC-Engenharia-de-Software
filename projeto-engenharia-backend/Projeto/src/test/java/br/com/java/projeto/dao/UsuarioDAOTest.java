package br.com.java.projeto.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.java.projeto.domain.Pessoa;
import br.com.java.projeto.domain.Usuario;

public class UsuarioDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Long codigoPessoa = 2L;

		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigoPessoa);

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();

		usuario.setSenha("1234");
		usuario.setTipo('G');
		usuario.setAtivo(false);
		usuario.setPessoa(pessoa);

		usuarioDAO.salvar(usuario);

		System.out.println("Registro salvo com sucesso!");
	}

	@Test
	@Ignore
	public void listar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> resultado = usuarioDAO.listar();

		System.out.println("Registros encontrados: " + resultado.size());

		for (Usuario usuario : resultado) {
			System.out.println("Codigo: " + usuario.getCodigo());
			System.out.println("Senha: " + usuario.getSenha());
			System.out.println("Tipo: " + usuario.getTipo());
			System.out.println("Status ativo: " + usuario.getAtivo());
			System.out.println("Nome: " + usuario.getPessoa().getNome());
			System.out.println();
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 3L;

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigo);

		System.out.println("Codigo: " + usuario.getCodigo());
		System.out.println("Senha: " + usuario.getSenha());
		System.out.println("Tipo: " + usuario.getTipo());
		System.out.println("Status ativo: " + usuario.getAtivo());
		System.out.println("Nome: " + usuario.getPessoa().getNome());
		System.out.println();
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 3L;

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigo);

		usuarioDAO.excluir(usuario);

		System.out.println("Registro excluido");
		System.out.println("Codigo: " + usuario.getCodigo());
		System.out.println("Senha: " + usuario.getSenha());
		System.out.println("Tipo: " + usuario.getTipo());
		System.out.println("Status ativo: " + usuario.getAtivo());
		System.out.println("Nome: " + usuario.getPessoa().getNome());
	}

	@Test
	@Ignore
	public void editar() {
		Long codigoPessoa = 2L;

		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigoPessoa);
		
		Long codigoUsuario = 2L;
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigoUsuario);
		
		System.out.println("Registro a ser editado:");
		System.out.println("Codigo: " + usuario.getCodigo());
		System.out.println("Senha: " + usuario.getSenha());
		System.out.println("Tipo: " + usuario.getTipo());
		System.out.println("Status ativo: " + usuario.getAtivo());
		System.out.println("Nome: " + usuario.getPessoa().getNome());
		
		usuario.setSenha("CorsaPreto987");
		usuario.setTipo('G');
		usuario.setAtivo(true);
		usuario.setPessoa(pessoa);
		
		usuarioDAO.editar(usuario);
		
		System.out.println("Registro editado:");
		System.out.println("Codigo: " + usuario.getCodigo());
		System.out.println("Senha: " + usuario.getSenha());
		System.out.println("Tipo: " + usuario.getTipo());
		System.out.println("Status ativo: " + usuario.getAtivo());
		System.out.println("Nome: " + usuario.getPessoa().getNome());
	}
}
