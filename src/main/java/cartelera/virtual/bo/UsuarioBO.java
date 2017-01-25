package cartelera.virtual.bo;

import java.util.List;

import cartelera.virtual.dto.UsuarioDTO;
import cartelera.virtual.entidades.Permiso;
import cartelera.virtual.entidades.Usuario;
import cartelera.virtual.exception.FindException;

public interface UsuarioBO extends GenericBO<Usuario> {
	public UsuarioDTO login(String username, String password) throws FindException;
	public List<Permiso> getPermisosByUser(String username) throws FindException;
}
