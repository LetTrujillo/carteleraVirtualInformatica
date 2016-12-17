package cartelera.virtual.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="CARTELERA")
public class Cartelera implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3264623642743012112L;

	@Id
	@GeneratedValue
	@Column(name="CARTELERA_ID")
	private Long id;
	
	private String titulo;
	
	private boolean activo;
	
//	@OneToMany(mappedBy="cartelera")
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cartelera", cascade = CascadeType.ALL)
	private List<Publicacion> publicaciones = new ArrayList<Publicacion>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}
	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}
}
