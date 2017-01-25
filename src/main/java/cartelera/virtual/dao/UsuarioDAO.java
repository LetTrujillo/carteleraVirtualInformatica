package cartelera.virtual.dao;

import java.util.List;

import cartelera.virtual.entidades.Alumno;
import cartelera.virtual.entidades.Docente;
import cartelera.virtual.entidades.Permiso;
import cartelera.virtual.entidades.Usuario;
import cartelera.virtual.exception.FindException;

public interface UsuarioDAO extends GenericDAO<Usuario> {
	public List<Alumno> findAllAlumnos() throws FindException;
	public List<Docente> findAllDocentes() throws FindException;
	public Docente checkLoginDocente(String username, String password) throws FindException;
	public Alumno checkLoginAlumno(String username, String password) throws FindException;
	public Usuario checkLogin(String username, String password) throws FindException;
	public List<Permiso> getPermisosByUser(String username) throws FindException;
}
