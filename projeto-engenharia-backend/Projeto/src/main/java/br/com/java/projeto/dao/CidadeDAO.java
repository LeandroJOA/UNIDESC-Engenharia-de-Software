package br.com.java.projeto.dao;

import br.com.java.projeto.domain.Cidade;
import br.com.java.projeto.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CidadeDAO extends GenericDAO<Cidade> {

    //Metodo especifico para listar as cidades dependendo do estado selecionado
    public List<Cidade> buscarPorEstado(Long estadoCodigo) {

        //Abertura da sessão
        Session sessao = HibernateUtil.getSessionFactory().openSession();

        try {
            //Captura da sessão
            Criteria consulta = sessao.createCriteria(Cidade.class);
            //Criação do WHERE, selecionando as linhas de cidade que tiverem FK igual a PK
            //do Estado selecionado
            consulta.add(Restrictions.eq("estado.codigo", estadoCodigo));
            //Ordenação destas linha pela coluna "Nome"
            consulta.addOrder(Order.asc("nome"));
            //Atribuição das linhas encontradas à List "resultado"
            List<Cidade> resultado = consulta.list();
            //Retorno das linha encontradas
            return resultado;
        } catch (RuntimeException error) {
            //Propagação do erro
            throw error;
        } finally {
            //Fechamento da sessão
            sessao.close();
        }
    }
}
