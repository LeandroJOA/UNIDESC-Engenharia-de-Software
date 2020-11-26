package br.com.java.projeto.bean;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//@ManagedBean - Indica que esta classe é um Bean
//@RequestScoped - Determina será feito um novo request a cada clique. Assim o arquivo enviado será recarregado
//continuamente
@ManagedBean
@RequestScoped
public class ImagemBean {

    //Indica que o parametro denominado "caminho" que será passado à esta classe, será atribuido ao atributo "caminho"
    @ManagedProperty("#{param.caminho}")
    //Responsavel pelo caminho da foto enviada
    private String caminho;
    //Responsavel por representar visualmente a foto enviada
    //StreamedContent - Classe responsavel por guardar informações de bytes de um arquivo para o "p:graphicImage"
    private StreamedContent foto;

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public StreamedContent getFoto() throws IOException {
        //Condição para caso nenhuma foto tenha sido enviado por upload
        if (caminho == null || caminho.isEmpty()) {
            //Caminho da foto em branco (arquivo default de 1 pixel)
            Path path = Paths.get("D:/Dev/Java WEB/Uploads/branco.png");
            //Captura fluxo de bytes da foto referente ao "path"
            InputStream stream = Files.newInputStream(path);
            //Atribui esse fluxo de bytes à foto que será retornada
            foto = new DefaultStreamedContent(stream);
        //Condição para caso o usuario tenha enviado uma foto
        } else {
            //Caminho da foto enviada pelo upload
            Path path = Paths.get(caminho);
            //Captura fluxo de bytes da foto referente ao "path"
            InputStream stream = Files.newInputStream(path);
            //Atribui esse fluxo de bytes à foto que será retornada
            foto = new DefaultStreamedContent(stream);
        }
        //Retorna a foto enviada pelo usuario para o "p:graphicImage"
        return foto;
    }

    public void setFoto(StreamedContent foto) {
        this.foto = foto;
    }
}
