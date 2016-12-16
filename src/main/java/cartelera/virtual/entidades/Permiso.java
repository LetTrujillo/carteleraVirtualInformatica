package cartelera.virtual.entidades;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="PERMISO")
public class Permiso implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -994397601269082098L;

	@Id
	@GeneratedValue
	@Column(name="PERMISO_ID")
	private Long id;
	
	@OneToOne(optional=false)
	private Cartelera cartelera;
	
	@OneToOne(optional=false)
	private Usuario usuario;
	
	private boolean puede_editar;
	private boolean puede_publicar;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public boolean isPuede_editar() {
		return puede_editar;
	}
	public void setPuede_editar(boolean puede_editar) {
		this.puede_editar = puede_editar;
	}
	public boolean isPuede_publicar() {
		return puede_publicar;
	}
	public void setPuede_publicar(boolean puede_publicar) {
		this.puede_publicar = puede_publicar;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cartelera getCartelera() {
		return cartelera;
	}
	public void setCartelera(Cartelera cartelera) {
		this.cartelera = cartelera;
	}
	
		

}
