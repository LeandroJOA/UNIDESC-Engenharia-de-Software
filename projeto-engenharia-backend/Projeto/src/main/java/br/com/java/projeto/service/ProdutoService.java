package br.com.java.projeto.service;

import br.com.java.projeto.dao.ProdutoDAO;
import br.com.java.projeto.domain.Produto;
import com.google.gson.Gson;

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
    public String editar(String json) {

        Gson gson = new Gson();
        //Converte a String de entrada em um objeto do tipo Fabricante e o armazena em um objeto
        Produto produto = gson.fromJson(json, Produto.class);

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
    public String excluir(String json) {

        Gson gson = new Gson();
        //Converte a String de entrada em um objeto do tipo Fabricante e o armazena em um objeto
        Produto produto = gson.fromJson(json, Produto.class);

        //Busca pelo objeto persistente referente ao codigo passado por input
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produto = produtoDAO.buscar(produto.getCodigo());

        //Chamada do metodo "editar", passando o objeto convertido
        produtoDAO.excluir(produto);

        //Conversão do objeto novamente para um Json
        String jsonSaida = gson.toJson(produto);

        //Retorno do Json convertido para feedback
        return jsonSaida;
    }
}