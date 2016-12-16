package cartelera.virtual.controller;

import org.springframework.beans.factory.annotation.Autowired;

import cartelera.virtual.bo.GenericBO;
import cartelera.virtual.bo.SuscripcionBO;
import cartelera.virtual.entidades.Cartelera;
import cartelera.virtual.entidades.Comentario;
import cartelera.virtual.entidades.ContenidoPublicacion;
import cartelera.virtual.entidades.Permiso;
import cartelera.virtual.entidades.Publicacion;
import cartelera.virtual.entidades.Usuario;

public abstract class AbstractController {
	
	@Autowired
	private GenericBO<Cartelera> carteleraBO;
	
	@Autowired
	private GenericBO<Usuario> usuarioBO;
	
	@Autowired
	private GenericBO<Comentario> comentarioBO;
	
	@Autowired
	private GenericBO<ContenidoPublicacion> contenidoPublicacionBO;
	
	@Autowired
	private GenericBO<Permiso> permisoBO;
	
	@Autowired
	private GenericBO<Publicacion> publicacionBO;
	
	@Autowired
	private SuscripcionBO suscripcionBO;
	
	
	
	public AbstractController() {
		// TODO Auto-generated constructor stub
	}

	public GenericBO<Cartelera> getCarteleraBO() {
		return carteleraBO;
	}

	public void setCarteleraBO(GenericBO<Cartelera> carteleraBO) {
		this.carteleraBO = carteleraBO;
	}

	public GenericBO<Usuario> getUsuarioBO() {
		return usuarioBO;
	}

	public void setUsuarioBO(GenericBO<Usuario> usuarioBO) {
		this.usuarioBO = usuarioBO;
	}

	public GenericBO<Comentario> getComentarioBO() {
		return comentarioBO;
	}

	public void setComentarioBO(GenericBO<Comentario> comentarioBO) {
		this.comentarioBO = comentarioBO;
	}

	public GenericBO<ContenidoPublicacion> getContenidoPublicacionBO() {
		return contenidoPublicacionBO;
	}

	public void setContenidoPublicacionBO(GenericBO<ContenidoPublicacion> contenidoPublicacionBO) {
		this.contenidoPublicacionBO = contenidoPublicacionBO;
	}

	public GenericBO<Permiso> getPermisoBO() {
		return permisoBO;
	}

	public void setPermisoBO(GenericBO<Permiso> permisoBO) {
		this.permisoBO = permisoBO;
	}

	public GenericBO<Publicacion> getPublicacionBO() {
		return publicacionBO;
	}

	public void setPublicacionBO(GenericBO<Publicacion> publicacionBO) {
		this.publicacionBO = publicacionBO;
	}

	public SuscripcionBO getSuscripcionBO() {
		return suscripcionBO;
	}

	public void setSuscripcionBO(SuscripcionBO suscripcionBO) {
		this.suscripcionBO = suscripcionBO;
	}

	
}
