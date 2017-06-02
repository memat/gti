package br.mbsistemas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.unoesc.exception.DAOException;
import br.mbsistemas.model.MinhaEntidade;

public abstract class HibernateDAO<T extends MinhaEntidade> implements GenericDAO<T> {

	protected EntityManagerFactory emf;
	protected EntityManager em;
	protected EntityTransaction et;
	
	protected void conectar() {
		emf = Persistence.createEntityManagerFactory("unidade");
		em = emf.createEntityManager();
	}

	protected void finalizar() {
		em.close();
		emf.close();
	}
	
	@Override
	public void salvar(T entidade) throws DAOException {
		this.conectar();
		et = em.getTransaction();
		try {
			et.begin();
			if (entidade.getCodigo() == null) {
				em.persist(entidade);	
			} else {
				em.merge(entidade);
			}
			et.commit();
		} catch (PersistenceException ex) {
			if (et.isActive()) {
				et.rollback();	
			}
			throw new DAOException("Erro ao Salvar", ex.getCause());
		} finally {
			this.finalizar();
		}
	}


	@Override
	public void excluir(T entidade) throws DAOException {
		this.conectar();
		et = em.getTransaction();
		try {
			et.begin();
			Object t = em.find(entidade.getClass(), entidade.getCodigo());
			em.remove(t);
			et.commit();
		} catch (PersistenceException ex) {
			if (et.isActive()) {
				et.rollback();	
			}
			throw new DAOException("Erro ao Excluir", ex.getCause());
		} finally {
			this.finalizar();
		}
	}

	@Override
	public T buscar(Class<T> classe, Long codigo) {
		this.conectar();
		try {
			return em.find(classe, codigo);
		} finally {
			this.finalizar();
		}
	}

	@Override
	public List<T> buscar(Class<T> classe, String descricao) {
		this.conectar();
		try {
			// String hql = "from "+classe.getName()+" a where upper(a.nome) like ?";
			// TypedQuery<T> query = em.createQuery(hql, classe);
			// TypedQuery<T> query = em.createNativeQuery("SELECT * FROM ALUNO", classe);
			TypedQuery<T> query = em.createNamedQuery("FILTRA_POR_NOME", classe);
			query.setParameter(1, "%"+descricao.toUpperCase()+"%");
			return query.getResultList();	
		} finally {
			this.finalizar();
		}
	}

	@Override
	public List<T> listar(Class<T> classe) {
		this.conectar();
		try {
			TypedQuery<T> query = em.createNamedQuery("TODOS_"+classe.getSimpleName().toUpperCase(), classe);
			return query.getResultList();
		} finally {
			this.finalizar();
		}
	}


}
