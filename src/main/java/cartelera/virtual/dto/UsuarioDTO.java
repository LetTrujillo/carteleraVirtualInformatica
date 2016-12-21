package cartelera.virtual.dto;

import java.util.List;

public class UsuarioDTO {
	
	private Long id;
	private String nombreUsuario;
	
	public UsuarioDTO() {
		super();
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

		
}
