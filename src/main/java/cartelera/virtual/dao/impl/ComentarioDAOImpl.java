package cartelera.virtual.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cartelera.virtual.dao.ComentarioDAO;
import cartelera.virtual.entidades.Comentario;

@Repository("comentarioDAO")
@Transactional
public class ComentarioDAOImpl extends GenericDAOImpl<Comentario> implements ComentarioDAO {

	public ComentarioDAOImpl(){
		super(Comentario.class);
	}

}
