package cartelera.virtual.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="PUBLICACION")
public class Publicacion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3798997152300108566L;
	
	@Id
	@GeneratedValue
	@Column(name="PUBLICACION_ID")
	private Long id;
	private String titulo;
	private String descripcion;
	private Date fechaPublicacion;
	private boolean activo;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="cartelera_CARTELERA_ID")
	private Cartelera cartelera;
	
	@OneToMany(cascade={CascadeType.ALL})
	private List<ContenidoPublicacion> archivos;
	
	@OneToMany(cascade={CascadeType.ALL})
	private List<Comentario> comentarios;
	
	@ManyToOne
	private Usuario propietario;
	
	public Cartelera getCartelera() {
		return cartelera;
	}
	public void setCartelera(Cartelera cartelera) {
		this.cartelera = cartelera;
	}
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public Usuario getPropietario() {
		return propietario;
	}
	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}
	public List<ContenidoPublicacion> getArchivos() {
		return archivos;
	}
	public void setArchivos(List<ContenidoPublicacion> archivos) {
		this.archivos = archivos;
	}
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
}
