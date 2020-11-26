package br.com.java.projeto.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.java.projeto.util.HibernateUtil;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDAO<Entidade> {
	private Class<Entidade> classe;

	// Capturar tipo classe especifica
	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

	}

	public void salvar(Entidade entidade) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
				transacao = sessao.beginTransaction();
				sessao.save(entidade);
				transacao.commit();
			} catch (RuntimeException error) {
				if (transacao != null) {
					transacao.rollback();
					throw error;
				}
			} finally {
				sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Entidade> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(classe);
			List<Entidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException error) {
			throw error;
		} finally {
			sessao.close();
		}
	}

	//Listagem ordenada para os Selects
	@SuppressWarnings("unchecked")
	public List<Entidade> listar(String campoOrdenacao) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			Criteria consulta = sessao.createCriteria(classe);
			//Ordenação ascendente pelo campo passado como parametro
			consulta.addOrder(Order.asc(campoOrdenacao));
			List<Entidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException error) {
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Entidade buscar(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(classe);
			consulta.add(Restrictions.idEq(codigo));
			Entidade resultado = (Entidade) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException error) {
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	public void excluir(Entidade entidade) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(entidade);
			transacao.commit();
		} catch (RuntimeException error) {
			if (transacao != null) {
				transacao.rollback();
				throw error;
			}
		} finally {
			sessao.close();
		}
	}
	
	public void editar(Entidade entidade) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(entidade);
			transacao.commit();
		} catch (RuntimeException error) {
			if (transacao != null) {
				transacao.rollback();
				throw error;
			}
		} finally {
			sessao.close();
		}
	}

	public Entidade merge(Entidade entidade) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			//Captura o objeto salvo junto de sua chave primaria
			Entidade retorno = (Entidade) sessao.merge(entidade);
			transacao.commit();
			//Retorna este objeto
			return retorno;
		} catch (RuntimeException error) {
			if (transacao != null) {
				transacao.rollback();
				throw error;
			}
		} finally {
			sessao.close();
		}
		return null;
	}
}
