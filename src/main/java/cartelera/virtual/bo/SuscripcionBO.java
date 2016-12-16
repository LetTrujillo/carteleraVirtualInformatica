package cartelera.virtual.bo;

import java.util.List;

import cartelera.virtual.entidades.Cartelera;
import cartelera.virtual.entidades.Suscripcion;
import cartelera.virtual.entidades.Usuario;
import javassist.NotFoundException;

public interface SuscripcionBO extends GenericBO<Suscripcion>{
	public List<Usuario> findSuscriptors(Cartelera cartelera) throws NotFoundException;


}
