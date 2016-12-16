package cartelera.virtual.entidades;


import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="USUARIO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) 
@DiscriminatorColumn(name="USUARIO_PERFIL")
public abstract class Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2782164915542244667L;
	
	@Id
	@GeneratedValue
	@Column(name="USUARIO_ID")
	private Long id;
	
	@Column(name="USERNAME")
	private String nombreUsuario;
	
	@OneToMany(cascade={CascadeType.ALL})
	@JoinTable(name="USUARIO_PERMISO", 
	joinColumns=@JoinColumn(name="USUARIO_ID",referencedColumnName="USUARIO_ID"), 
	inverseJoinColumns=@JoinColumn(name="PERMISO_ID",referencedColumnName="PERMISO_ID"))
	private List<Permiso> permisos;

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="APELLIDO")
	private String apellido;

	@Column(name="EMAIL")
	private String email;

	private String password;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public List<Permiso> getPermisos() {
		return permisos;
	}
	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
