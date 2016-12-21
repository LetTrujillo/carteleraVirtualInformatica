package cartelera.virtual.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cartelera.virtual.bo.impl.GuaraniBOImpl;
import cartelera.virtual.dto.AlumnoDTO;
import cartelera.virtual.dto.DocenteDTO;
import cartelera.virtual.dto.LoginInfoDTO;
import cartelera.virtual.exception.FindException;
import cartelera.virtual.exception.SaveException;
import javassist.NotFoundException;

@RestController
public class GuaraniAPIController {

	@Autowired
	private GuaraniBOImpl guaraniBO;

	@RequestMapping(value="/guarani-api/crearAlumnosDePrueba", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<AlumnoDTO>> crearAlumnosDePrueba(){
		try {
			List<AlumnoDTO> usuariosCreados = new ArrayList<AlumnoDTO>(); 
			usuariosCreados.add(guaraniBO.createAlumno("Juan", "Perez", "jp@globalemail.com", "12345"));
			usuariosCreados.add(guaraniBO.createAlumno("Joaquin", "Sabina", "sabina@myemail.com", "54321"));
			usuariosCreados.add(guaraniBO.createAlumno("Lucas", "Napoli", "lnapoli@globalemail.com", "1111"));
			usuariosCreados.add(guaraniBO.createAlumno("Leticia", "Trujillo", "ltrujillo@globalemail.com", "2222"));
			usuariosCreados.add(guaraniBO.createAlumno("Pedro", "Albertuz", "palbertuz@superemail.com", "12345"));

			return new ResponseEntity<List<AlumnoDTO>>(usuariosCreados, HttpStatus.OK);
		} catch (SaveException e) {
			e.printStackTrace();
			return new ResponseEntity<List<AlumnoDTO>>(new ArrayList<AlumnoDTO>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value="/guarani-api/crearDocentesDePrueba", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<DocenteDTO>> crearDocentesDePrueba(){
		try {
			List<DocenteDTO> usuariosCreados = new ArrayList<DocenteDTO>(); 
			usuariosCreados.add(guaraniBO.createDocente("Leonidas", "Primero", "thisissparta@globalemail.com", "2222"));
			usuariosCreados.add(guaraniBO.createDocente("Charo", "Jimenez", "char@superemail.com", "12345"));
			usuariosCreados.add(guaraniBO.createDocente("Agustin", "Gomez", "ag@globalemail.com", "5555"));
			usuariosCreados.add(guaraniBO.createDocente("Carlos", "Gutierrez", "carlitos@globalemail.com", "2222"));

			return new ResponseEntity<List<DocenteDTO>>(usuariosCreados, HttpStatus.OK);
		} catch (SaveException e) {
			e.printStackTrace();
			return new ResponseEntity<List<DocenteDTO>>(new ArrayList<DocenteDTO>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value="/guarani-api/alumnos/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AlumnoDTO> getAlumno(@PathVariable("id") long id){
		try{
			AlumnoDTO alumno = guaraniBO.getAlumno(id);
			return new ResponseEntity<AlumnoDTO>(alumno,HttpStatus.OK);
		}catch(FindException e){
			e.printStackTrace();
			return new ResponseEntity<AlumnoDTO>(new AlumnoDTO(),HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<AlumnoDTO>(new AlumnoDTO(),HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value="/guarani-api/alumnos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<AlumnoDTO>> getAlumnos(){
		try {
			List<AlumnoDTO> alumnos = guaraniBO.getAlumnos();

			HttpStatus resultStatus = HttpStatus.OK;
			if(alumnos.size() == 0)
				resultStatus = HttpStatus.NO_CONTENT;

			return new ResponseEntity<List<AlumnoDTO>>(alumnos,resultStatus);
		} catch (FindException e) {
			e.printStackTrace();
			return new ResponseEntity<List<AlumnoDTO>>(new ArrayList<AlumnoDTO>(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value="/guarani-api/profesores/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<DocenteDTO> getProfesor(@PathVariable("id") long id){
		try{
			DocenteDTO docente = guaraniBO.getDocente(id);
			return new ResponseEntity<DocenteDTO>(docente,HttpStatus.OK);
		}catch(FindException e){
			e.printStackTrace();
			return new ResponseEntity<DocenteDTO>(new DocenteDTO(),HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<DocenteDTO>(new DocenteDTO(),HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value="/guarani-api/profesores", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<DocenteDTO>> getProfesores(){
		try {
			List<DocenteDTO> docentes = guaraniBO.getDocentes();

			HttpStatus resultStatus = HttpStatus.OK;
			if(docentes.size() == 0)
				resultStatus = HttpStatus.NO_CONTENT;

			return new ResponseEntity<List<DocenteDTO>>(docentes,resultStatus);
		} catch (FindException e) {
			e.printStackTrace();
			return new ResponseEntity<List<DocenteDTO>>(new ArrayList<DocenteDTO>(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/guarani-api/alumnos/chequearlogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AlumnoDTO> chequearAlumnoLogin(@RequestBody LoginInfoDTO login){
		try {
			if(login != null && login.getUsername() != null && login.getPassword() != null && !login.getUsername().equals("") && !login.getPassword().equals("")){
				AlumnoDTO loginUser = guaraniBO.alumnoLogin(login.getUsername(), login.getPassword());

				return new ResponseEntity<AlumnoDTO>(loginUser,HttpStatus.OK);
			}else{
				return new ResponseEntity<AlumnoDTO>(new AlumnoDTO(),HttpStatus.NO_CONTENT);
			}
		} catch (FindException e) {
			e.printStackTrace();
			return new ResponseEntity<AlumnoDTO>(new AlumnoDTO(),HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "/guarani-api/profesores/chequearlogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<DocenteDTO> chequearDocenteLogin(@RequestBody LoginInfoDTO login){
		try {
			if(login != null && login.getUsername() != null && login.getPassword() != null && !login.getUsername().equals("") && !login.getPassword().equals("")){
				DocenteDTO loginUser = guaraniBO.docenteLogin(login.getUsername(), login.getPassword());

				return new ResponseEntity<DocenteDTO>(loginUser,HttpStatus.OK);
			}else{
				return new ResponseEntity<DocenteDTO>(new DocenteDTO(),HttpStatus.NO_CONTENT);
			}
		} catch (FindException e) {
			e.printStackTrace();
			return new ResponseEntity<DocenteDTO>(new DocenteDTO(),HttpStatus.UNAUTHORIZED);
		}
	}

	public GuaraniBOImpl getUserService() {
		return guaraniBO;
	}

	public void setUserService(GuaraniBOImpl userService) {
		this.guaraniBO = userService;
	}
}
