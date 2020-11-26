package br.com.java.projeto.service;

import br.com.java.projeto.dao.FabricanteDAO;
import br.com.java.projeto.domain.Fabricante;
import com.google.gson.Gson;

import javax.ws.rs.*;
import java.util.List;

//Define o caminho/nome utilizado para a chamada deste serviço
@Path("fabricante")
public class FabricanteService {

    //Metodo para listar todas as linhas do banco
    //Define o metodo HTTP a ser usado para a chamada deste serviço
    //Get - captura informação atraves da URL
    //Url para chamar este serviço - http:localhost:8080/rest/fabricante
    @GET
    public String listar() {
        //Chamada do metodo "Listar" e atribuição à um "ArrayList"
        FabricanteDAO fabricanteDAO = new FabricanteDAO();
        List<Fabricante> fabricantes = fabricanteDAO.listar("descricao");

        //Instanciação do Gson
        Gson gson = new Gson();
        //Chamada do metodo "toJson", responsavel por converter um objeto em uma String
        String json = gson.toJson(fabricantes);

        //Retorna o resultado da conversão da lista "Fabricantes" em "Json"
        return json;
    }

    //Metodo para listar uma linha especifica do banco
    @GET
    //Informa que após a "/" na url, o que for inserido será armazenado como parametro "codigo"
    //Url para chamar este serviço - http:localhost:8080/rest/fabricante/{parametro}
    @Path("{codigo}")
    //@PathParam - Realiza a amarra entre o "codigo" passado pela url, com o "codigo" do tipo Long que será recebido
    //por este metodo
    public String buscar(@PathParam("codigo") Long codigo) {

        //Chamada do metodo "Buscar" e atribuição à um objeto
        FabricanteDAO fabricanteDAO = new FabricanteDAO();
        Fabricante fabricante = fabricanteDAO.buscar(codigo);

        //Instanciação do Gson
        Gson gson = new Gson();
        //Chamada do metodo "toJson", responsavel por converter um objeto em uma String
        String json = gson.toJson(fabricante);

        //Retorna o resultado da conversão da lista "Fabricantes" em "Json"
        return json;
    }

    //Metodo POST para salvar um novo fabricante
    //Url para chamar este serviço - http:localhost:8080/rest/fabricante
    @POST
    public String salvar(String json) {

        Gson gson = new Gson();
        //Converte a String de entrada em um objeto do tipo Fabricante e o armazena em um objeto
        Fabricante fabricante = gson.fromJson(json, Fabricante.class);

        //Chamada do metodo "salvar", passando o objeto convertido
        FabricanteDAO fabricanteDAO = new FabricanteDAO();
        fabricanteDAO.salvar(fabricante); //Utilizando o metodo "merge" seria possivel editar um fabricante utilizando
                                          //o metodo POST

        //Conversão do objeto novamente para um Json
        String jsonSaida = gson.toJson(fabricante);

        //Retorno do Json convertido para feedback
        return jsonSaida;
    }

    //Metodo PUT para editar um fabricante já existente
    //Url para chamar este serviço - http:localhost:8080/rest/fabricante
    @PUT
    public String editar(String json) {

        Gson gson = new Gson();
        //Converte a String de entrada em um objeto do tipo Fabricante e o armazena em um objeto
        Fabricante fabricante = gson.fromJson(json, Fabricante.class);

        //Chamada do metodo "editar", passando o objeto convertido
        FabricanteDAO fabricanteDAO = new FabricanteDAO();
        fabricanteDAO.editar(fabricante);

        //Conversão do objeto novamente para um Json
        String jsonSaida = gson.toJson(fabricante);

        //Retorno do Json convertido para feedback
        return jsonSaida;
    }

    //Metodo DELETE para excluir um fabricante já existente
    //Url para chamar este serviço - http:localhost:8080/rest/fabricante
    @DELETE
    public String excluir(String json) {

        Gson gson = new Gson();
        //Converte a String de entrada em um objeto do tipo Fabricante e o armazena em um objeto
        Fabricante fabricante = gson.fromJson(json, Fabricante.class);

        //Busca pelo objeto persistente referente ao codigo passado por input
        FabricanteDAO fabricanteDAO = new FabricanteDAO();
        fabricante = fabricanteDAO.buscar(fabricante.getCodigo());

        //Chamada do metodo "editar", passando o objeto convertido
        fabricanteDAO.excluir(fabricante);

        //Conversão do objeto novamente para um Json
        String jsonSaida = gson.toJson(fabricante);

        //Retorno do Json convertido para feedback
        return jsonSaida;
    }
}
