package cartelera.virtual.bo.impl;

import java.io.Serializable;

import cartelera.virtual.bo.GenericBO;
import cartelera.virtual.dao.GenericDAO;
import cartelera.virtual.exception.DeleteException;
import cartelera.virtual.exception.SaveException;
import cartelera.virtual.exception.UpdateException;
import javassist.NotFoundException;

public class GenericBOImpl<T> implements GenericBO<T> {
	
	private GenericDAO<T> dao;

	public GenericBOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public T update(T entity) throws UpdateException {
		
		return this.getDao().update(entity);
	}



	@Override
	public void remove(Serializable id) throws DeleteException, NotFoundException {
		
		this.getDao().remove(id);
	}



	@Override
	public boolean exists(Serializable id) {
		
		return this.getDao().exists(id);
	}



	@Override
	public T save(T entity) throws SaveException {
		
		return this.getDao().save(entity);
	}



	@Override
	public T find(Serializable id) throws NotFoundException {
		
		return this.getDao().find(id);
	}

	
	public GenericDAO<T> getDao() {
		return dao;
	}

	public void setDao(GenericDAO<T> dao) {
		this.dao = dao;
	}
	
	
}
