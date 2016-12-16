package cartelera.virtual.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cartelera.virtual.dao.ContenidoPublicacionDAO;
import cartelera.virtual.entidades.ContenidoPublicacion;

@Repository("contenidoPublicacionDAO")
@Transactional
public class ContenidoPublicacionDAOImpl extends GenericDAOImpl<ContenidoPublicacion> implements ContenidoPublicacionDAO {

	public ContenidoPublicacionDAOImpl(){
		super(ContenidoPublicacion.class);
	}

}
