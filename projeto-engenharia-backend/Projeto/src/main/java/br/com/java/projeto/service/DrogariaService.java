package br.com.java.projeto.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

//Define o caminho/nome utilizado para a chamada deste serviço
//http://localhost:8080/Projeto/rest/drogaria (Utilizando o TomCat pelo IntelliJ, não
//é necessario explicitar o "/Projeto"
@Path("drogaria")
public class DrogariaService {

    //Define o metodo HTTP a ser usado para a chamada deste serviço
    //Get - captura informação atraves da URL
    @GET
    public String exibir() {

        return "Curso de Java";
    }
}
