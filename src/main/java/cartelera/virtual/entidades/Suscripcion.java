package cartelera.virtual.entidades;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="SUSCRIPCION")
public class Suscripcion implements Serializable{

	private static final long serialVersionUID = 289222838955913864L;
	
	@Id
	@GeneratedValue
	@Column(name="SUSCRIPCION_ID")
	private Long id;
	
	@OneToOne
	private Usuario suscriptor;
	
	@OneToOne
	private Cartelera cartelera;
	
	public Suscripcion() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getSuscriptor() {
		return suscriptor;
	}
	public void setSuscriptor(Usuario suscriptor) {
		this.suscriptor = suscriptor;
	}
	public Cartelera getCartelera() {
		return cartelera;
	}
	public void setCartelera(Cartelera cartelera) {
		this.cartelera = cartelera;
	}
	
	
	
}
