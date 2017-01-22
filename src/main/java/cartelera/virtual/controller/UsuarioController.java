package cartelera.virtual.controller;


import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
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

import cartelera.virtual.bo.GenericBO;
import cartelera.virtual.common.error.ResponseError;
import cartelera.virtual.dto.UsuarioDTO;
import cartelera.virtual.entidades.Alumno;
import cartelera.virtual.entidades.Usuario;
import cartelera.virtual.exception.DeleteException;
import cartelera.virtual.exception.SaveException;
import cartelera.virtual.exception.UpdateException;
import javassist.NotFoundException;

@RestController
public class UsuarioController{
	
	@Autowired
	private GenericBO<Usuario> usuarioBO;
	
	private Mapper mapper;
	
	/**
	 * Recupera un usuario por id
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/usuario/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> usuarioById(@PathVariable("id") Long id){
		try {
			Usuario usuario = this.getUsuarioBO().find(id);
			
			UsuarioDTO retval = mapper.map(usuario, UsuarioDTO.class);
			return new ResponseEntity<UsuarioDTO>(retval, HttpStatus.OK);
		} catch (NotFoundException e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
			error.setError("UsuarioController - Ocurrió un error al recuperar el usuario " + id);
			return new ResponseEntity<ResponseError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Crea un usuario de tipo alumno
	 * @param usuario
	 * @return Usuario
	 */
	@RequestMapping(value = "/usuario/alumno", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> saveAlumno(@RequestBody UsuarioDTO usuario) {
		try {
			Usuario alumno = new Alumno();
			alumno.setNombreUsuario(usuario.getNombreUsuario());
			
			alumno = this.getUsuarioBO().save(alumno);
			UsuarioDTO retval = mapper.map(alumno, UsuarioDTO.class);
			return new ResponseEntity<UsuarioDTO>(retval, HttpStatus.OK);
		} catch (SaveException e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
			error.setError("UsuarioController - Ocurrió un error al intentar guardar el usuario " + usuario.getNombreUsuario());
			return new ResponseEntity<ResponseError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	/**
	 * Actualiza un usuario de tipo alumno
	 * @param usuario
	 * @return
	 */
	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> updateAlumno(@RequestBody UsuarioDTO usuario) {
		try {
			Usuario alumno = new Alumno();
			alumno.setId(usuario.getId());
			alumno.setNombreUsuario(usuario.getNombreUsuario());
			
			alumno = this.getUsuarioBO().update(alumno);
			UsuarioDTO retval = mapper.map(alumno, UsuarioDTO.class);
			
			return new ResponseEntity<UsuarioDTO>(retval, HttpStatus.OK);
		} catch (UpdateException e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
			error.setError("UsuarioController - Ocurrió un error al intentar actualizar el usuario " + usuario.getId());
			return new ResponseEntity<ResponseError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Elimina un usuario
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<?> delete(@PathVariable("id") Long id){
		try {
			this.getUsuarioBO().remove(id);
			return new ResponseEntity<String>("El usuario fue eliminado", HttpStatus.OK);
		} catch (DeleteException | NotFoundException e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
			error.setError("UsuarioController - Ocurrió un error al intentar eliminar el usuario " + id);
			return new ResponseEntity<ResponseError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/usuario", method = RequestMethod.GET)
	public ResponseEntity<?> getAll(){
		try {
			List<Usuario> usuarioList = new ArrayList<Usuario>();
			usuarioList = this.getUsuarioBO().getAll(Usuario.class);
			
			List<UsuarioDTO> retval = new ArrayList<UsuarioDTO>();
			HttpStatus resultStatus = HttpStatus.OK;
			
			if(usuarioList == null || usuarioList.size() == 0){
				resultStatus = HttpStatus.NO_CONTENT;
			}else{
				for(Usuario cart : usuarioList){
					retval.add(mapper.map(cart, UsuarioDTO.class));
				}
			}

			return new ResponseEntity<List<UsuarioDTO>>(retval,resultStatus);

		} catch (NotFoundException e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
			error.setError("CarteleraController - Ocurrió un error al recuperar la cartelera " );
			return new ResponseEntity<ResponseError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public GenericBO<Usuario> getUsuarioBO() {
		return usuarioBO;
	}

	public void setUsuarioBO(GenericBO<Usuario> usuarioBO) {
		this.usuarioBO = usuarioBO;
	}

	public UsuarioController(){
		mapper = new DozerBeanMapper();
	}
	

}
