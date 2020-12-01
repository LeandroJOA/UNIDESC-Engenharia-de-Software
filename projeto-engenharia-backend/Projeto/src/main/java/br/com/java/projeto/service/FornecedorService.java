package br.com.java.projeto.service;

import br.com.java.projeto.dao.FornecedorDAO;
import br.com.java.projeto.dao.ProdutoDAO;
import br.com.java.projeto.domain.Fornecedor;
import br.com.java.projeto.domain.Produto;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
}
