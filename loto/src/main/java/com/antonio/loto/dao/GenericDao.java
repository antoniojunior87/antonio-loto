package com.antonio.loto.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericDao {

	private EntityManagerFactory entityManagerFactory;

	public EntityManagerFactory getEntityManagerFactory() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("P_UNIT");
		}
		return entityManagerFactory;
	}

	public <T> void salvarTodos(List<T> lista) {
		EntityManager entityManager = getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		for (T entidade : lista) {
			entityManager.persist(entidade);
		}
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void salvar(Object entidade) {
		EntityManager entityManager = getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(entidade);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> listarTodos(String namedQuery) {
		EntityManager entityManager = getEntityManagerFactory().createEntityManager();
		return (List<T>) entityManager.createNamedQuery(namedQuery).getResultList();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> listarPorPeriodo(String namedQuery, Date inicio, Date fim) {
		EntityManager entityManager = getEntityManagerFactory().createEntityManager();
		return (List<T>) entityManager.createNamedQuery(namedQuery).setParameter("inicio", inicio)
				.setParameter("fim", fim).getResultList();
	}
}
