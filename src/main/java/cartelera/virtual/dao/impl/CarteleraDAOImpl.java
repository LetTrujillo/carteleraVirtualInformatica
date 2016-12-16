package cartelera.virtual.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cartelera.virtual.dao.CarteleraDAO;
import cartelera.virtual.entidades.Cartelera;

@Repository("carteleraDAO")
@Transactional
public class CarteleraDAOImpl extends GenericDAOImpl<Cartelera> implements CarteleraDAO {

	public CarteleraDAOImpl(){
		super(Cartelera.class);
	}

}
