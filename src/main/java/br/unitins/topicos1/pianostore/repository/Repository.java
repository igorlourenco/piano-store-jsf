package br.unitins.topicos1.pianostore.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Repository<T> {
	
	private EntityManager entityManager = null;
	
	protected EntityManager getEntityManager() {
		if (entityManager == null) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("PianoStore");
			entityManager = emf.createEntityManager();
		}
		return entityManager;
	}
	
	public void salvar(T obj) throws Exception {
		try {
			getEntityManager().getTransaction().begin();
			getEntityManager().merge(obj);
			getEntityManager().getTransaction().commit();
		} catch (Exception e){
			e.printStackTrace();
			
			throw new Exception("Erro ao salvar.");
		}
	}
	
	public void deletar(T obj) {
		if (obj != null) {
			getEntityManager().getTransaction().begin();
			getEntityManager().remove(getEntityManager().merge(obj));
			getEntityManager().getTransaction().commit();
		}

	}

}
