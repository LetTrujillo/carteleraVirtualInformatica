package cartelera.virtual.dto;

public class PermisoDTO {
	
	private Long cartelera; 
	private Long usuario;
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

	public Long getCartelera() {
		return cartelera;
	}

	public void setCartelera(Long cartelera) {
		this.cartelera = cartelera;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	public boolean isPuede_publicar() {
		return puede_publicar;
	}

	public void setPuede_publicar(boolean puede_publicar) {
		this.puede_publicar = puede_publicar;
	}
	
	

	
}
