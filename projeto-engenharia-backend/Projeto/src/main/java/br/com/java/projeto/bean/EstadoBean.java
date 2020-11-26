package br.com.java.projeto.bean;

import br.com.java.projeto.dao.EstadoDAO;
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
public class EstadoBean implements Serializable {
	private Estado estado;
	private List<Estado> estados;

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Estado getEstado() {
		return estado;
	}

	//PostConstruct - Realizar a listagem dos dados logo após o metodo construtor desta classe ser chamado (EstadoBean)
	@PostConstruct
	public void listar(){
        try {
            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError(("ERROR ao listar!"));
            erro.printStackTrace();
        }
    }

	//instanciação do objeto por um metodo
	public void novo(){
		estado = new Estado();
	}

	public void salvar() {
		/*
		String msg = "Mensagem de Teste!";
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		*/

		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.merge(estado);

			//Limpeza de campos
			novo();
			//Atualização da listagem
			estados = estadoDAO.listar();

			Messages.addGlobalInfo("Estado salvo com sucesso!");
		}catch (RuntimeException erro){
			Messages.addGlobalError("ERROR ao salvar!");
			//Imprimir erro no log do console
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento){
    	try {
			//Receber atributos passados pela view
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");

			//Chamada do metodo excluir
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.excluir(estado);

			//Atuailização dos registros
			estados = estadoDAO.listar();

			Messages.addGlobalInfo("Estado excluido com sucesso!");
		} catch (RuntimeException erro){
    		Messages.addGlobalError("ERROR ao excluir!");
    		erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento){
		//Receber atributos passados pela view
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
	}
}
