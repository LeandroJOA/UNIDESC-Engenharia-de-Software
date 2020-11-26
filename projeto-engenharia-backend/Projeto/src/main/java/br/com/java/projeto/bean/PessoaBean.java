package br.com.java.projeto.bean;

import br.com.java.projeto.dao.CidadeDAO;
import br.com.java.projeto.dao.EstadoDAO;
import br.com.java.projeto.dao.PessoaDAO;
import br.com.java.projeto.domain.Cidade;
import br.com.java.projeto.domain.Estado;
import br.com.java.projeto.domain.Pessoa;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
//implements - Para evitar advertencias de serialização
public class PessoaBean implements Serializable {

    //Objeto
    private Pessoa pessoa;
    //Objeto temporario de Estado
    private Estado estado;
    //Lista com os dados para a tabela
    private List<Pessoa> pessoas;
    //Lista com os dados para o select
    private List<Cidade> cidades;
    private List<Estado> estados;


    //Gets e Sets
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    //Metodo para listar todos os dados da tabela ao carregar a tela
    //PostConstruct - Realizar a listagem dos dados logo após o metodo construtor desta classe
    //ser chamado
    @PostConstruct
    public void listar() {
        try {
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoas = pessoaDAO.listar("nome"); //"nome" - Campo a ser ordenado
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
            pessoa = new Pessoa();
            estado = new Estado();

            //Populando menu de seleção
            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar("nome");

            //Criando seleção vazia
            cidades = new ArrayList<>();
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
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoaDAO.merge(pessoa);

            //Limpeza de campos
            pessoa = new Pessoa();
            estado = new Estado();

            //Atualização da tabela
            pessoas = pessoaDAO.listar("nome");

            //Populando menu de seleção
            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar("nome");

            //Criando seleção vazia
            cidades = new ArrayList<>();

            //Mensagem de sucesso
            Messages.addGlobalInfo("Produto salvo com sucesso!");
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
            pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

            //Chamada do metodo excluir
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoaDAO.excluir(pessoa);

            //Atualização dos registros
            pessoas = pessoaDAO.listar("nome");

            //Mensagem de sucesso
            Messages.addGlobalInfo("Pessoa excluida com sucesso!");
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
            pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

            //Receber estado selecionado
            estado = pessoa.getCidade().getEstado();

            //Populando menu de seleção
            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar("nome");

            CidadeDAO cidadeDAO = new CidadeDAO();
            cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
        } catch (RuntimeException erro) {
            //Mensagem de erro
            Messages.addGlobalError("ERROR ao listar seleção!");
            //Imprimir erro no log do console
            erro.printStackTrace();
        }
    }

    //Metodo para popular "Cidades" com base no "Estado" selecionado
    public void popular() {

        try {
            //Verifica se foi selecionado uma opção valida
            if (estado != null) {
                //Popula select "Cidade"
                CidadeDAO cidadeDAO = new CidadeDAO();
                cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
            } else {
                //Torna o select "Cidade" vazio
                cidades = new ArrayList<>();
            }
        } catch (RuntimeException erro) {
            //Mensagem de erro
            Messages.addGlobalError("ERROR ao listar seleção!");
            //Imprimir erro no log do console
            erro.printStackTrace();
        }
    }
}
