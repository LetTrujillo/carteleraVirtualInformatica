package cartelera.virtual.entidades;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="CONTENIDO_PUBLICACION")
public class ContenidoPublicacion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2846356032512790986L;
	
	@Id
	@GeneratedValue
	@Column(name="CONTENIDO_PUBLICACION_ID")
	private Long id;
	private String path;
	private String mime_type;
	
	@ManyToOne
	private Publicacion publicacion;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getMime_type() {
		return mime_type;
	}
	public void setMime_type(String mime_type) {
		this.mime_type = mime_type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Publicacion getPublicacion() {
		return publicacion;
	}
	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}
	

}
