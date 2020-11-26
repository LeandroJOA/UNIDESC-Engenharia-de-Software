package br.com.java.projeto.bean;

import br.com.java.projeto.dao.CidadeDAO;
import br.com.java.projeto.dao.EstadoDAO;
import br.com.java.projeto.domain.Cidade;
import br.com.java.projeto.domain.Estado;
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
public class CidadeBean implements Serializable {
    private Cidade cidade;
    private List<Cidade> cidades;
    private List<Estado> estados;

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
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

    //PostConstruct - Realizar a listagem dos dados logo após o metodo construtor desta classe ser chamado (EstadoBean)
    @PostConstruct
    public void listar() {
        try {
            CidadeDAO cidadeDAO = new CidadeDAO();
            cidades = cidadeDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("ERROR ao listar!");
            //Imprimir erro no log do console
            erro.printStackTrace();
        }
    }

    public void novo() {
        try {
            //instanciação do objeto por um metodo
            cidade = new Cidade();

            //Populando menu de seleção
            EstadoDAO estadoDAO = new EstadoDAO();
            //("nome") - Campo a ser ordenado
            estados = estadoDAO.listar("nome");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("ERROR ao listar seleção!");
            //Imprimir erro no log do console
            erro.printStackTrace();
        }
    }

    public void salvar() {
        try {
            CidadeDAO cidadeDAO = new CidadeDAO();
            cidadeDAO.merge(cidade);

            //Limpeza de campos
            cidade = new Cidade();

            //Atualzação da tabela
            cidades = cidadeDAO.listar();

            //Atualização do select
            EstadoDAO estadoDAO = new EstadoDAO();
            //("nome") - Campo a ser ordenado
            estados = estadoDAO.listar("nome");

            Messages.addGlobalInfo("Cidade salva com sucesso!");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("ERROR ao salvar!");
            //Imprimir erro no log do console
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento){
        try {
            //Receber atributos passados pela view
            cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");

            //Chamada do metodo excluir
            CidadeDAO cidadeDAO = new CidadeDAO();
            cidadeDAO.excluir(cidade);

            //Atuailização dos registros
            cidades = cidadeDAO.listar();

            Messages.addGlobalInfo("Cidade excluida com sucesso!");
        } catch (RuntimeException erro){
            Messages.addGlobalError("ERROR ao excluir!");
            //Imprimir erro no log do console
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento){
        try {
            //Receber atributos passados pela view
            cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");

            //Populando menu de seleção
            EstadoDAO estadoDAO = new EstadoDAO();
            //("nome") - Campo a ser ordenado
            estados = estadoDAO.listar("nome");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("ERROR ao listar seleção!");
            //Imprimir erro no log do console
            erro.printStackTrace();
        }
    }
}
