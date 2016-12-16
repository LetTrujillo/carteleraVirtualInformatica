package cartelera.virtual.dao;

import java.util.List;

import cartelera.virtual.entidades.Cartelera;
import cartelera.virtual.entidades.Suscripcion;
import cartelera.virtual.entidades.Usuario;
import javassist.NotFoundException;

public interface SuscripcionDAO extends GenericDAO<Suscripcion> {
	public List<Usuario> findSuscriptors(Cartelera cartelera) throws NotFoundException;
}
