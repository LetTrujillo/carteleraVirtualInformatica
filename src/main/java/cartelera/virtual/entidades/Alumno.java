package cartelera.virtual.entidades;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ALUMNO")
public class Alumno extends Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1453561212497946133L;
	
	public static final String USUARIO_PERFIL = "ALUMNO";
	
}
