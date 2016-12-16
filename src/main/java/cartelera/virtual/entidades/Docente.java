package cartelera.virtual.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue("DOCENTE")
public class Docente extends Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4677733378549392827L;
	
	public static final String USUARIO_PERFIL = "DOCENTE";
	
	@ElementCollection
	private List<Integer> anios;
	
	public List<Integer> getAnios() {
		return anios;
	}
	public void setAnios(List<Integer> anios) {
		this.anios = anios;
	}
	
	
	
}
