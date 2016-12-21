package cartelera.virtual.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cartelera.virtual.bo.GuaraniBO;
import cartelera.virtual.dao.UsuarioDAO;
import cartelera.virtual.dto.AlumnoDTO;
import cartelera.virtual.dto.DocenteDTO;
import cartelera.virtual.entidades.Alumno;
import cartelera.virtual.entidades.Docente;
import cartelera.virtual.entidades.Usuario;
import cartelera.virtual.exception.FindException;
import cartelera.virtual.exception.SaveException;
import javassist.NotFoundException;

@Service
public class GuaraniBOImpl implements GuaraniBO{
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	private Mapper mapper;

	public List<AlumnoDTO> getAlumnos() throws FindException{
		List<AlumnoDTO> result = new ArrayList<AlumnoDTO>();
		
		List<Alumno> alumnos = usuarioDAO.findAllAlumnos();
		
		for(Alumno alumno : alumnos){
			result.add(mapper.map(alumno, AlumnoDTO.class));
		}
		
		return result;
	}
	
	public AlumnoDTO getAlumno(long id) throws FindException, NotFoundException{
		Usuario alumno = usuarioDAO.find(id);
		
		return mapper.map(alumno, AlumnoDTO.class);
	}
	
	public List<DocenteDTO> getDocentes() throws FindException{
		List<DocenteDTO> result = new ArrayList<DocenteDTO>();
		
		List<Docente> docentes = usuarioDAO.findAllDocentes();
		
		for(Docente docente : docentes){
			result.add(mapper.map(docente, DocenteDTO.class));
		}
		
		return result;
	}
	
	public DocenteDTO getDocente(long id) throws FindException, NotFoundException{
		Usuario alumno = usuarioDAO.find(id);
		
		return mapper.map(alumno, DocenteDTO.class);
	}
	
	public AlumnoDTO createAlumno(String nombre, String apellido, String email, String password) throws SaveException{
		Alumno newAlumno = new Alumno();
		newAlumno.setNombre(nombre);
		newAlumno.setApellido(apellido);
		newAlumno.setEmail(email);
		newAlumno.setNombreUsuario(nombre.toLowerCase()+"_"+apellido.toLowerCase());
		newAlumno.setPassword(password);
		usuarioDAO.save(newAlumno);
		
		return mapper.map(newAlumno, AlumnoDTO.class);
	}
	
	public DocenteDTO createDocente(String nombre, String apellido, String email, String password) throws SaveException{
		Docente newDocente = new Docente();
		newDocente.setNombre(nombre);
		newDocente.setApellido(apellido);
		newDocente.setEmail(email);
		newDocente.setNombreUsuario(nombre.toLowerCase()+"_"+apellido.toLowerCase());
		newDocente.setPassword(password);
		usuarioDAO.save(newDocente);
		
		return mapper.map(newDocente, DocenteDTO.class);
	}
	
	public AlumnoDTO alumnoLogin(String username, String password) throws FindException{
		Alumno alumnoLogin = usuarioDAO.checkLoginAlumno(username, password);
		return mapper.map(alumnoLogin, AlumnoDTO.class);
	}
	
	public DocenteDTO docenteLogin(String username, String password) throws FindException{
		Docente docenteLogin = usuarioDAO.checkLoginDocente(username, password);
		return mapper.map(docenteLogin, DocenteDTO.class);
	}
	
	public GuaraniBOImpl(){
		mapper = new DozerBeanMapper();
	}
	
	public Mapper getMapper() {
		return mapper;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}
	
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
}
