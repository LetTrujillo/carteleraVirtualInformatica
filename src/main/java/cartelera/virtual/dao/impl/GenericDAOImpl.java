package cartelera.virtual.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cartelera.virtual.dao.GenericDAO;
import cartelera.virtual.exception.DeleteException;
import cartelera.virtual.exception.SaveException;
import cartelera.virtual.exception.UpdateException;
import javassist.NotFoundException;

@Transactional
public class GenericDAOImpl<T> implements GenericDAO<T> {

	protected Class<T> persistentClass;
	private EntityManager entityManager;

	public GenericDAOImpl(Class<T> persistentClass){
		this.persistentClass = persistentClass;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager em){ 
		this.entityManager = em;
	}

	public EntityManager getEntityManager() {
		return entityManager; 
	}

	@Override
	public T save(T entity) throws SaveException {
		EntityManager em = getEntityManager(); 
		try {
			em.persist(entity);
		}
		catch (RuntimeException e) {
			throw new SaveException("Ocurrió un error al tratar de guardar la entidad "+ entity.toString());
		}
		finally {
			em.close();
		}
		return entity;
	}

	@Override
	public T update(T entity) throws UpdateException {
		EntityManager em = getEntityManager(); 
		try {
			em.merge(entity);
		}
		catch (RuntimeException e) {
			throw new UpdateException("Ocurrió un error al tratar de actualizar la entidad "+ entity.toString());
		}
		finally {
			em.close();
		}
		return entity;
	}

	@Override
	public void remove(Serializable id) throws DeleteException, NotFoundException {
		T entity = getEntityManager().find(this.getPersistentClass(), id); 
		if (entity != null) {
			EntityManager em = getEntityManager(); 
			try {
				em.remove(em.contains(entity) ? entity : em.merge(entity));
			}
			catch (RuntimeException e) {
				throw new DeleteException("Ocurrió un error al tratar de eliminar la entidad "+ entity.toString());
			}
			finally {
				em.close();
			}
		}else{
			throw new NotFoundException("No se encontró la entidad con id "+id.toString());
		}
	}

	@Override
	public boolean exists(Serializable id) {
		T entity = getEntityManager().find(this.getPersistentClass(), id); 
		return entity != null;
	}

	@Override
	public T find(Serializable id) throws NotFoundException {
		T entity = getEntityManager().find(this.getPersistentClass(), id); 
		if (entity != null) {
			return entity;
		}else{
			throw new NotFoundException("No se encontró la entidad con id "+id.toString());
		}
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll(Class<T> entityClass) throws NotFoundException {
		
		Query query = getEntityManager().createQuery("from " + entityClass.getName());
		return (List<T>)query.getResultList();
	}
}
