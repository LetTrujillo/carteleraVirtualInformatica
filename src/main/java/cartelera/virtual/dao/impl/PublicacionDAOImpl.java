package cartelera.virtual.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cartelera.virtual.dao.PublicacionDAO;
import cartelera.virtual.entidades.Publicacion;

@Repository("publicacionDAO")
@Transactional
public class PublicacionDAOImpl extends GenericDAOImpl<Publicacion> implements PublicacionDAO {

	public PublicacionDAOImpl(){
		super(Publicacion.class);
	}

}
