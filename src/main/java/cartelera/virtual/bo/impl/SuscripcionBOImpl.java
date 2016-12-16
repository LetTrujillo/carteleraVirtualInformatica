package cartelera.virtual.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cartelera.virtual.bo.SuscripcionBO;
import cartelera.virtual.dao.SuscripcionDAO;
import cartelera.virtual.entidades.Cartelera;
import cartelera.virtual.entidades.Suscripcion;
import cartelera.virtual.entidades.Usuario;
import javassist.NotFoundException;

public class SuscripcionBOImpl extends GenericBOImpl<Suscripcion> implements SuscripcionBO {
	
	@Autowired
	private SuscripcionDAO dao;

	@Override
	public List<Usuario> findSuscriptors(Cartelera cartelera) throws NotFoundException {
		return this.getDao().findSuscriptors(cartelera);
	}

	public SuscripcionDAO getDao() {
		return dao;
	}

	public void setDao(SuscripcionDAO dao) {
		this.dao = dao;
	}

	
	
}
