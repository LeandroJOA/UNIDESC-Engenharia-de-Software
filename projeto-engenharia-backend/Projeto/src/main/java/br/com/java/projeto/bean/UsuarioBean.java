package br.com.java.projeto.bean;

import br.com.java.projeto.dao.PessoaDAO;
import br.com.java.projeto.dao.UsuarioDAO;
import br.com.java.projeto.domain.Pessoa;
import br.com.java.projeto.domain.Usuario;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
//implements - Para evitar advertencias de serialização
public class UsuarioBean implements Serializable {

    //Objeto
    private Usuario usuario;
    //Lista com os dados para a tabela
    private List<Usuario> usuarios;
    //Lista com os dados para o select
    private List<Pessoa> pessoas;

    //Gets e Sets
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    //Metodo para listar todos os dados da tabela ao carregar a tela
    //PostConstruct - Realizar a listagem dos dados logo após o metodo construtor desta classe
    //ser chamado
    @PostConstruct
    public void listar() {

        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarios = usuarioDAO.listar("tipo");
        } catch (RuntimeException erro) {
            //Mensagem de erro
            Messages.addGlobalError("ERROR ao listar!");
            //Imprimir erro no log do console
            erro.printStackTrace();
        }
    }

    //Metodo para o botão "Novo"
    public void novo() {
        try {
            //instanciação do objeto por um metodo e limpeza de campos
            usuario = new Usuario();

            //Populando menu de seleção
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoas = pessoaDAO.listar("nome"); //"nome" - Campo a ser ordenado
        } catch (RuntimeException erro) {
            //Mensagem de erro
            Messages.addGlobalError("ERROR ao listar seleção!");
            //Imprimir erro no log do console
            erro.printStackTrace();
        }
    }

    //Metodo para o botão "Salvar"
    public void salvar() {

        try {
            //Chamada do metodo merge
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.merge(usuario);

            //Limpeza de campos
            usuario = new Usuario();

            //Atualização da tabela
            usuarios = usuarioDAO.listar("tipo");

            //Populando menu de seleção
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoas = pessoaDAO.listar("nome");

            //Mensagem de sucesso
            Messages.addGlobalInfo("Usuario salvo com sucesso!");
        } catch (RuntimeException erro) {
            //Mensagem de erro
            Messages.addGlobalError("ERROR ao salvar!");
            //Imprimir erro no log do console
            erro.printStackTrace();
        }
    }

    //Metodo para o botão "Excluir"
    public void excluir(ActionEvent evento) {

        try {
            //Receber atributos da view
            usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

            //Chamada do metodo excluir
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.excluir(usuario);

            //Atualização dos registros
            usuarios = usuarioDAO.listar("tipo");

            //Mensagem de sucesso
            Messages.addGlobalInfo("Usuario excluido com sucesso!");
        } catch (RuntimeException erro) {
            //Mensagem de erro
            Messages.addGlobalError("ERROR ao excluir!");
            //Imprimir erro no log do console
            erro.printStackTrace();
        }
    }

    //Metodo para preencher a janela de formulario com os dados da linha selecionada
    public void editar(ActionEvent evento) {

        try {
            //Receber atributos da view
            usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

            //Populando menu de seleção
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoas = pessoaDAO.listar("nome");
        } catch (RuntimeException erro) {
            //Mensagem de erro
            Messages.addGlobalError("ERROR ao listar seleção!");
            //Imprimir erro no log do console
            erro.printStackTrace();
        }
    }
}
