package br.com.java.projeto.service;

import br.com.java.projeto.dao.FornecedorDAO;
import br.com.java.projeto.dao.ProdutoDAO;
import br.com.java.projeto.domain.Fornecedor;
import br.com.java.projeto.domain.Produto;
import com.google.gson.Gson;

import javax.ws.rs.*;
import java.util.List;

@Path("fornecedor")
public class FornecedorService {

    @GET
    public String listar() {
        //Chamada do metodo "Listar" e atribuição à um "ArrayList"
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        List<Fornecedor> fornecedores = fornecedorDAO.listar("descricao");

        //Instanciação do Gson
        Gson gson = new Gson();
        //Chamada do metodo "toJson", responsavel por converter um objeto em uma String
        String json = gson.toJson(fornecedores);

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
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        Fornecedor fornecedor = fornecedorDAO.buscar(codigo);

        //Instanciação do Gson
        Gson gson = new Gson();
        //Chamada do metodo "toJson", responsavel por converter um objeto em uma String
        String json = gson.toJson(fornecedor);

        //Retorna o resultado da conversão da lista "Fabricantes" em "Json"
        return json;
    }

    //Metodo POST para salvar um novo fabricante
    //Url para chamar este serviço - http:localhost:8080/rest/fabricante
    @POST
    public String salvar(String json) {

        Gson gson = new Gson();
        //Converte a String de entrada em um objeto do tipo Fabricante e o armazena em um objeto
        Fornecedor fornecedor = gson.fromJson(json, Fornecedor.class);

        //Chamada do metodo "salvar", passando o objeto convertido
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        fornecedorDAO.salvar(fornecedor); //Utilizando o metodo "merge" seria possivel editar um fabricante utilizando
        //o metodo POST

        //Conversão do objeto novamente para um Json
        String jsonSaida = gson.toJson(fornecedor);

        //Retorno do Json convertido para feedback
        return jsonSaida;
    }

    //Metodo PUT para editar um fabricante já existente
    //Url para chamar este serviço - http:localhost:8080/rest/fabricante
    @PUT
    public String editar(String json) {

        Gson gson = new Gson();
        //Converte a String de entrada em um objeto do tipo Fabricante e o armazena em um objeto
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
    //Url para chamar este serviço - http:localhost:8080/rest/fabricante
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
