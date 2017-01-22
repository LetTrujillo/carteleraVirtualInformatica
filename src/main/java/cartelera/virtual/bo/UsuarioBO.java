package cartelera.virtual.bo;

import cartelera.virtual.dto.UsuarioDTO;
import cartelera.virtual.entidades.Usuario;
import cartelera.virtual.exception.FindException;

public interface UsuarioBO extends GenericBO<Usuario> {
	public UsuarioDTO login(String username, String password) throws FindException;
}
