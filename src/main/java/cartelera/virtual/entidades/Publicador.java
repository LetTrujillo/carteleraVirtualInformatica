package cartelera.virtual.entidades;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@DiscriminatorValue("PUBLICADOR")
public class Publicador extends Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 882815893705925864L;

}
