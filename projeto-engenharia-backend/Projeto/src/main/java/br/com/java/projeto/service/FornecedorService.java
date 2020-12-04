package br.com.java.projeto.service;

import br.com.java.projeto.dao.FornecedorDAO;
import br.com.java.projeto.domain.Fornecedor;
import com.google.gson.Gson;

import javax.ws.rs.*;
import java.util.List;

@Path("fornecedor")
public class FornecedorService {

    @GET
    public String listar() {
        //Chamada do metodo "Listar" e atribuição à um "ArrayList"
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        List<Fornecedor> fornecedores = fornecedorDAO.listar();

        //Instanciação do Gson
        Gson gson = new Gson();
        //Chamada do metodo "toJson", responsavel por converter um objeto em uma String
        String json = gson.toJson(fornecedores);

        //Retorna o resultado da conversão
        return json;
    }

    //Metodo para listar uma linha especifica do banco
    @GET
    //Informa que após a "/" na url, o que for inserido será armazenado como parametro "codigo"
    @Path("{codigo}")
    //@PathParam - Realiza a amarra entre o "codigo" passado pela url, com o "codigo" do tipo Long que será recebido
    //por este metodo
    public String buscar(@PathParam("codigo") Long codigo) {

        //Chamada do metodo "Buscar" e atribuição à um objeto
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        Fornecedor fornecedor = fornecedorDAO.buscar(codigo);

        //Instanciação do Gson
        Gson gson = new Gson();
        //Chamada do metodo "toJson", responsavel por converter um objeto em uma String
        String json = gson.toJson(fornecedor);

        //Retorna o resultado da conversão
        return json;
    }

    //Metodo POST
    @POST
    public String salvar(String json) {

        Gson gson = new Gson();
        //Converte a String de entrada em um objeto
        Fornecedor fornecedor = gson.fromJson(json, Fornecedor.class);

        //Chamada do metodo "salvar", passando o objeto convertido
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        fornecedorDAO.salvar(fornecedor);

        //Conversão do objeto novamente para um Json
        String jsonSaida = gson.toJson(fornecedor);

        //Retorno do Json convertido para feedback
        return jsonSaida;
    }

    //Metodo PUT
    @PUT
    public String editar(String json) {

        Gson gson = new Gson();
        //Converte a String de entrada em um objeto
        Fornecedor fornecedor = gson.fromJson(json, Fornecedor.class);

        //Chamada do metodo "editar", passando o objeto convertido
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        fornecedorDAO.editar(fornecedor);

        //Conversão do objeto novamente para um Json
        String jsonSaida = gson.toJson(fornecedor);

        //Retorno do Json convertido para feedback
        return jsonSaida;
    }

    //Metodo DELETE para excluir um fabricante já existente
    @DELETE
    @Path("{codigo}")
    public String excluir(@PathParam("codigo") Long codigo) {

        Gson gson = new Gson();

        //Busca pelo objeto persistente referente ao codigo passado por input
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        Fornecedor fornecedor = fornecedorDAO.buscar(codigo);

        //Chamada do metodo "editar", passando o objeto convertido
        fornecedorDAO.excluir(fornecedor);

        //Conversão do objeto novamente para um Json
        String jsonSaida = gson.toJson(fornecedor);

        //Retorno do Json convertido para feedback
        return jsonSaida;
    }
}
