package br.com.java.projeto.bean;

import br.com.java.projeto.dao.ClienteDAO;
import br.com.java.projeto.dao.PessoaDAO;
import br.com.java.projeto.domain.Cliente;
import br.com.java.projeto.domain.Pessoa;
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
public class ClienteBean implements Serializable {

    //Objeto
    private Cliente cliente;
    //Lista com os dados para a tabela
    private List<Cliente> clientes;
    //Lista com os dados para o select
    private List<Pessoa> pessoas;

    //Gets e Sets
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
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
            ClienteDAO clienteDAO = new ClienteDAO();
            clientes = clienteDAO.listar("dataCadastro"); //"dataCadastro" - Campo a ser ordenado
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
            cliente = new Cliente();

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

    //Metodo para o botão "Salvar"
    public void salvar() {

        try {
            //Chamada do metodo merge
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.merge(cliente);

            //Limpeza de campos
            cliente = new Cliente();

            //Atualização da tabela
            clientes = clienteDAO.listar("dataCadastro");

            //Populando menu de seleção
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoas = pessoaDAO.listar("nome");

            //Mensagem de sucesso
            Messages.addGlobalInfo("Cliente salvo com sucesso!");
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
            cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

            //Chamada do metodo excluir
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.excluir(cliente);

            //Atualização dos registros
            clientes = clienteDAO.listar("dataCadastro");

            //Mensagem de sucesso
            Messages.addGlobalInfo("Cliente excluido com sucesso!");
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
            cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

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
