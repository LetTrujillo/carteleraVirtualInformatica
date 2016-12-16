package cartelera.virtual.entidades;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@DiscriminatorValue("ADMINISTRADOR")
public class Administrador extends Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6703211406460434656L;
	
	public static final String USUARIO_PERFIL = "ADMINISTRADOR";
}
