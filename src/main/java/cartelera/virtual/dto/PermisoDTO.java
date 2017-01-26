package cartelera.virtual.dto;

public class PermisoDTO {
	
	private CarteleraDTO cartelera; 
	private UsuarioDTO usuario;
	private boolean puede_publicar;	
	private boolean puede_editar;

	public PermisoDTO() {
		super();
	}

	public boolean isPuede_editar() {
		return puede_editar;
	}

	public void setPuede_editar(boolean puede_editar) {
		this.puede_editar = puede_editar;
	}

	public CarteleraDTO getCartelera() {
		return cartelera;
	}

	public void setCartelera(CarteleraDTO cartelera) {
		this.cartelera = cartelera;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public boolean isPuede_publicar() {
		return puede_publicar;
	}

	public void setPuede_publicar(boolean puede_publicar) {
		this.puede_publicar = puede_publicar;
	}
	
	

	
}
