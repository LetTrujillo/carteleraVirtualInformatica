package cartelera.virtual.bo;

import java.util.List;

import cartelera.virtual.dto.AlumnoDTO;
import cartelera.virtual.dto.DocenteDTO;
import cartelera.virtual.exception.FindException;
import cartelera.virtual.exception.SaveException;
import javassist.NotFoundException;

public interface GuaraniBO {
	public List<AlumnoDTO> getAlumnos() throws FindException;
	public AlumnoDTO getAlumno(long id) throws FindException, NotFoundException;
	public List<DocenteDTO> getDocentes() throws FindException;
	public DocenteDTO getDocente(long id) throws FindException, NotFoundException;
	public AlumnoDTO createAlumno(String nombre, String apellido, String email, String password) throws SaveException;
	public DocenteDTO createDocente(String nombre, String apellido, String email, String password) throws SaveException;
	public AlumnoDTO alumnoLogin(String username, String password) throws FindException;
	public DocenteDTO docenteLogin(String username, String password) throws FindException;
}
