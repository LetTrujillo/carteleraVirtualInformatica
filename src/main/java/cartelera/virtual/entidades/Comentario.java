package cartelera.virtual.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="COMENTARIO")
public class Comentario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5654696431686366629L;

	@Id
	@GeneratedValue
	@Column(name="COMENTARIO_ID")
	private Long id;
	
	private String comentario;
	
	private Date fecha;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Publicacion publicacion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Publicacion getPublicacion() {
		return publicacion;
	}
	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}
	

}
