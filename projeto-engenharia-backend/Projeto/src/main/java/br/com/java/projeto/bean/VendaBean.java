package br.com.java.projeto.bean;

import br.com.java.projeto.dao.ProdutoDAO;
import br.com.java.projeto.domain.ItemVenda;
import br.com.java.projeto.domain.Produto;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
//implements - Para evitar advertencias de serialização
public class VendaBean implements Serializable {

    //Lista com os dados de produtos para a tabela
    private List<Produto> produtos;
    //Lista com os produtos que irão para o carrinho de compras
    private List<ItemVenda> itensVenda;

    //Gets e Sets
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    //Metodo para listar todos os dados da tabela ao carregar a tela
    //PostConstruct - Realiza a listagem dos dados logo após o metodo construtor desta classe
    //ser chamado
    @PostConstruct
    public void listar() {
        try {
            //Listagem de produtos
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtos = produtoDAO.listar("descricao");

            //Listagem de produtos no carrinho
            itensVenda = new ArrayList<>();
        } catch (RuntimeException erro) {
            //Mensagem de erro
            Messages.addGlobalError("ERROR ao listar!");
            //Imprimir erro no log do console
            erro.printStackTrace();
        }
    }

    public void adicionar(ActionEvent evento) {
        try {
            //Recebe o produto selecionado
            Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

            //Variavel de busca, onde:
            //-1 = Não encontrado
            //>=0 = Encontrado + Sua posição (6 = Encontrado na posição 6)
            int achou = -1;

            //Percorrendo a lista
            for (int i = 0; i < itensVenda.size(); i++) {
                //Verifica se o produto na posição atual da lista é igual ao produto recebido
                if (itensVenda.get(i).getProduto().equals(produto)){
                    //Determina a posição encontrada
                    achou = i;
                    //Interrompe o laço
                    break;
                }
            }

            //Verifica se o produto recebido já está no carrinho
            if (achou < 0) {
                //Caso não, cria um novo item no carrrinho

                //Converte o produto recebido em um ItemVenda
                ItemVenda itemVenda = new ItemVenda();
                itemVenda.setValorParcial(produto.getPreco());
                itemVenda.setProduto(produto);
                itemVenda.setQuantidade(new Short("1"));

                //Adiciona o ItemVenda ao ArrayList
                itensVenda.add(itemVenda);
            } else {
                //Caso sim, atualiza o item já existente

                //Captura o item na posição em que foi achado
                ItemVenda itemVenda = itensVenda.get(achou);

                //Determina que sua quantidade será igual a sua antiga + 1
                //OBS: Soma entre valores numericos resulta em um valor do tipo int
                //Portanto, após a operação, é necessario converte-lo de volta ao tipo Short
                itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));

                //Multiplica o valor unitario do produto selecionado pela sua quantidade
                //OBS: A classe BigDecimal não aceita operadores aritmeticos (+, -, *, /)
                //Portanto é necessario utilizar seu metodo proprietario "Multiply"
                //Todavia, este metodo só funciona entre valores do tipo BigDecimanl
                //Portanto, antes, é necessario converter sua quantidade (do tipo Short) em um BigDecimal
                itemVenda.setValorParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
            }

            //Mensagem de sucesso
            Messages.addGlobalInfo("Produto adicionado ao carrinho com sucesso");
        } catch (RuntimeException erro) {
            //Mensagem de erro
            Messages.addGlobalError("ERROR ao adicionar");
            //Imprimir erro no log do console
            erro.printStackTrace();
        }

    }
}
