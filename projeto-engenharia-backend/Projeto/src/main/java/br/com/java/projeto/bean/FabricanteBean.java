package br.com.java.projeto.bean;

import br.com.java.projeto.dao.FabricanteDAO;
import br.com.java.projeto.domain.Fabricante;
import org.omnifaces.util.Messages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ViewScoped
@ManagedBean
public class FabricanteBean implements Serializable {
    private Fabricante fabricante;

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public void novo(){
        fabricante = new Fabricante();
    }

    public void salvar(){
        try {
            FabricanteDAO fabricanteDAO = new FabricanteDAO();
            fabricanteDAO.salvar(fabricante);

            novo();

            Messages.addGlobalInfo("Fabricante salvo com sucesso!");
        } catch (RuntimeException erro){
            Messages.addGlobalError("ERROR ao salvar!");
            erro.printStackTrace();
        }
    }
}
