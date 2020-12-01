package br.com.java.projeto.service;

import br.com.java.projeto.dao.FornecedorDAO;
import br.com.java.projeto.dao.ProdutoDAO;
import br.com.java.projeto.domain.Fornecedor;
import br.com.java.projeto.domain.Produto;
import com.google.gson.Gson;
import com.sun.scenario.effect.impl.prism.PrDrawable;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("produto")
public class ProdutoService {

    @GET
    public String listar() {
        //Chamada do metodo "Listar" e atribuição à um "ArrayList"
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.listar("descricao");

        //Instanciação do Gson
        Gson gson = new Gson();
        //Chamada do metodo "toJson", responsavel por converter um objeto em uma String
        String json = gson.toJson(produtos);

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
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = produtoDAO.buscar(codigo);

        //Instanciação do Gson
        Gson gson = new Gson();
        //Chamada do metodo "toJson", responsavel por converter um objeto em uma String
        String json = gson.toJson(produto);

        //Retorna o resultado da conversão da lista "Fabricantes" em "Json"
        return json;
    }

    //Metodo POST para salvar um novo fabricante
    //Url para chamar este serviço - http:localhost:8080/rest/fabricante
    @POST
    public String salvar(String json) {

        Gson gson = new Gson();
        //Converte a String de entrada em um objeto do tipo Fabricante e o armazena em um objeto
        Produto produto = gson.fromJson(json, Produto.class);

        //Chamada do metodo "salvar", passando o objeto convertido
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.salvar(produto); //Utilizando o metodo "merge" seria possivel editar um fabricante utilizando
        //o metodo POST

        //Conversão do objeto novamente para um Json
        String jsonSaida = gson.toJson(produto);

        //Retorno do Json convertido para feedback
        return jsonSaida;
    }

    //Metodo PUT para editar um fabricante já existente
    //Url para chamar este serviço - http:localhost:8080/rest/fabricante
    @PUT
    @Path("{codigo}")
    public String editar(@PathParam("codigo") Long codigo) {

        Gson gson = new Gson();
        //Converte a String de entrada em um objeto do tipo Fabricante e o armazena em um objeto
        Produto produto = new Produto();

        //Chamada do metodo "editar", passando o objeto convertido
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.editar(produto);

        //Conversão do objeto novamente para um Json
        String jsonSaida = gson.toJson(produto);

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
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = produtoDAO.buscar(codigo);

        //Chamada do metodo "editar", passando o objeto convertido
        produtoDAO.excluir(produto);

        //Conversão do objeto novamente para um Json
        String jsonSaida = gson.toJson(produto);

        //Retorno do Json convertido para feedback
        return jsonSaida;
    }
}