package br.com.java.projeto.bean;

import br.com.java.projeto.dao.FabricanteDAO;
import br.com.java.projeto.dao.ProdutoDAO;
import br.com.java.projeto.domain.Fabricante;
import br.com.java.projeto.domain.Produto;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@ManagedBean
@ViewScoped
//implements - Para evitar advertencias de serialização
public class ProdutoBean implements Serializable {

    //Objeto
    private Produto produto;
    //Lista com os dados para a tabela
    private List<Produto> produtos;
    //Lista com os dados para o select
    private List<Fabricante> fabricantes;

    //Gets e Sets
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Fabricante> getFabricantes() {
        return fabricantes;
    }

    public void setFabricantes(List<Fabricante> fabricantes) {
        this.fabricantes = fabricantes;
    }

    //Metodo para listar todos os dados da tabela ao carregar a tela
    //PostConstruct - Realizar a listagem dos dados logo após o metodo construtor desta classe
    //ser chamado
    @PostConstruct
    public void listar() {
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtos = produtoDAO.listar();
        } catch (RuntimeException erro) {
            //Mensagem de erro
            Messages.addGlobalError("ERROR ao listar!");
            //Imprimir erro no log do console
            erro.printStackTrace();
        }
    }

    //Metodo para o botão "Novo"
    public void novo() {
        try {
            //instanciação do objeto por um metodo
            produto = new Produto();

            //Populando menu de seleção
            FabricanteDAO fabricanteDAO = new FabricanteDAO();
            fabricantes = fabricanteDAO.listar();
        } catch (RuntimeException erro) {
            //Mensagem de erro
            Messages.addGlobalError("ERROR ao listar seleção!");
            //Imprimir erro no log do console
            erro.printStackTrace();
        }
    }

    //Metodo para o botão "Salvar"
    public void salvar() {
        try {
            //Tornando o upload de um arquivo obrigatorio
            if (produto.getCaminho() == null) {
                Messages.addGlobalError("O campo \'Upload\' é obrigatório!");
                return;
            }

            //Chamada do metodo merge
            ProdutoDAO produtoDAO = new ProdutoDAO();
            //Armazena o objeto preenchido em "produtoRetorno"
            Produto produtoRetorno =  produtoDAO.merge(produto);

            //Caminho de origem do arquivo temporario
            Path origem = Paths.get(produto.getCaminho());
            //Caminho de destino final para este mesmo arquivo
            //onde ("/Caminho do diretorio final" + nomeDoArquivo + ".extensão"
            Path destino = Paths.get("D:/Dev/Java WEB/Uploads/" + produtoRetorno.getCodigo() + ".png");

            //Copia o arquivo temporario (origem) para o arquivo final (destino) utilizando o metodo de copia sem pergunta
            //Tornando assim p arquivo temporario enviado por upload, em um arquivo definitivo
            Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

            //Limpeza de campos
            produto = new Produto();

            //Atualização da tabela
            produtos = produtoDAO.listar();

            //Atualização do select
            FabricanteDAO fabricanteDAO = new FabricanteDAO();
            fabricantes = fabricanteDAO.listar();

            //Mensagem de sucesso
            Messages.addGlobalInfo("Produto salvo com sucesso!");
        // | - Indica que poderá ser tratado um erro RunTime ou IO
        } catch (RuntimeException | IOException erro) {
            //Mensagem de erro
            Messages.addGlobalError("ERROR ao salvar!");
            //Imprimir erro no log do console
            erro.printStackTrace();
        }
    }

    //Metodo para o botão "Excluir"
    public void excluir(ActionEvent evento) {
        try {
            //Receber atributos da view
            produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

            //Chamada do metodo excluir
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.excluir(produto);

            //Captura caminho do arquivo referente ao produto atual
            Path arquvio = Paths.get("D:/Dev/Java WEB/Uploads/" + produto.getCodigo() + ".png");
            //Deleta este arquivo, caso ele exista
            Files.deleteIfExists(arquvio);

            //Atualização dos registros
            produtos = produtoDAO.listar();

            //Mensagem de sucesso
            Messages.addGlobalInfo("Produto excluido com sucesso!");
        // | - Indica que poderá ser tratado um erro RunTime ou IO
        } catch (RuntimeException | IOException erro) {
            //Mensagem de erro
            Messages.addGlobalError("ERROR ao excluir!");
            //Imprimir erro no log do console
            erro.printStackTrace();
        }
    }

    //Metodo para preencher a janela de formulario com os dados da linha selecionada
    public void editar(ActionEvent evento) {
        try {
            //Receber atributos da view
            produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

            //Populando menu de seleção
            FabricanteDAO fabricanteDAO = new FabricanteDAO();
            fabricantes = fabricanteDAO.listar();

            //Preenche o caminho do produto atual com seu respectivo arquivo de upload (Caso não exista = null)
            produto.setCaminho("D:/Dev/Java WEB/Uploads/" + produto.getCodigo() + ".png");
        } catch (RuntimeException erro) {
            //Mensagem de erro
            Messages.addGlobalError("ERROR ao listar seleção!");
            //Imprimir erro no log do console
            erro.printStackTrace();
        }
    }

    //Metodo chamado pelo "p:fileUpload", responsavel pelo tratamento do arquivo enviado
    //Arquvio enviado fica armazendo no objeto "evento" da classe "FileUploadEvent"
    public void upload(FileUploadEvent evento) {
        try {
            //Captura o arquivo enviado
            UploadedFile arquivoUpload = evento.getFile();
            //Cria um arquivo temporario e captura seu caminho
            //prefix - nome do arquvio / suffix - extensão (null = default)
            Path arquivoTemp = Files.createTempFile(null, null);

            //Copia o arquivo enviado para o arquivo temporario criado
            //arquivoUpload.getInputstream() - Captura o caminho de byts do arquivo enviado
            //arquivoTemp - Arquivo de destino
            //StandardCopyOption.REPLACE_EXISTING - Metodo de copia (Substituir sem perguntar)
            Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);

            //Captura o caminho do arquivo temporario gerado (Já substituido pelo arquvio enviado por upload)
            produto.setCaminho(arquivoTemp.toString());

            //Mensagem de sucesso
            Messages.addGlobalInfo("Upload realizado com sucesso");
        } catch (IOException erro) {
            //Mensagem de erro
            Messages.addGlobalError("ERROR ao realizar upload!");
            //Imprimir erro no log do console
            erro.printStackTrace();
        }

    }
}
