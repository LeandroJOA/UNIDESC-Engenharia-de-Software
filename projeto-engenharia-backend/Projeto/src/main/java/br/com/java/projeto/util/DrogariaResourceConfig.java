package br.com.java.projeto.util;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

//Define o caminho/nome utilizado para a chamada do Restful
// http://localhost:8080/Projeto/rest
@ApplicationPath("rest")
//Classe para configuração dos recursos do Jersey
public class DrogariaResourceConfig extends ResourceConfig {

    //Utilização do metodo construtor para indicar pacote com os serviços da aplicação
    public DrogariaResourceConfig() {
        packages("br.com.java.projeto.service");
    }
}
