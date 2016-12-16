package cartelera.virtual.bo;

import java.io.Serializable;

import cartelera.virtual.exception.DeleteException;
import cartelera.virtual.exception.SaveException;
import cartelera.virtual.exception.UpdateException;
import javassist.NotFoundException;

public abstract interface GenericBO<T> {
	
	public T update(T entity) throws UpdateException;
	public void remove(Serializable id) throws DeleteException, NotFoundException; 
	public boolean exists(Serializable id); 
	public T save(T entity) throws SaveException;
	public T find(Serializable id) throws NotFoundException;
	

}
