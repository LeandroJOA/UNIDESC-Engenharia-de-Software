package br.com.java.projeto.bean;

import br.com.java.projeto.dao.FornecedorDAO;
import br.com.java.projeto.domain.Fornecedor;
import org.omnifaces.util.Messages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ViewScoped
@ManagedBean
public class FornecedorBean implements Serializable {
    private Fornecedor fornecedor;

    public Fornecedor getFabricante() {
        return fornecedor;
    }

    public void setFabricante(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void novo(){
        fornecedor = new Fornecedor();
    }

    public void salvar(){
        try {
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            fornecedorDAO.salvar(fornecedor);

            novo();

            Messages.addGlobalInfo("Fabricante salvo com sucesso!");
        } catch (RuntimeException erro){
            Messages.addGlobalError("ERROR ao salvar!");
            erro.printStackTrace();
        }
    }
}
