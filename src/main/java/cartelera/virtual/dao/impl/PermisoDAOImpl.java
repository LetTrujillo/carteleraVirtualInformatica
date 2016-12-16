package cartelera.virtual.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cartelera.virtual.dao.PermisoDAO;
import cartelera.virtual.entidades.Permiso;

@Repository("permisoDAO")
@Transactional
public class PermisoDAOImpl extends GenericDAOImpl<Permiso> implements PermisoDAO {

	public PermisoDAOImpl(){
		super(Permiso.class);
	}

}
