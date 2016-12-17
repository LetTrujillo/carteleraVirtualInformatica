package cartelera.virtual.controller;


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
@RequestMapping("/usuario")
public class UsuarioController extends AbstractController{
	
	@Autowired
	private GenericBO<Usuario> usuarioBO;
	
	/**
	 * Recupera un usuario por id
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> usuarioById(@PathVariable("id") Long id){
		try {
			return new ResponseEntity<Usuario>(this.getUsuarioBO().find(id), HttpStatus.OK);
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
	@RequestMapping(value = "/alumno", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> saveAlumno(@RequestBody UsuarioDTO usuario) {
		Usuario alumno = new Alumno();
		alumno.setNombreUsuario(usuario.getNombreUsuario());
		try {
			return new ResponseEntity<Usuario>(this.getUsuarioBO().save(alumno), HttpStatus.OK);
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
	@RequestMapping(value = "/updateAlumno", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> updateAlumno(@RequestBody UsuarioDTO usuario) {
		Usuario alumno = new Alumno();
		alumno.setId(usuario.getId());
		alumno.setNombreUsuario(usuario.getNombreUsuario());
		try {
			return new ResponseEntity<Usuario>(this.getUsuarioBO().update(alumno), HttpStatus.OK);
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
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
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

	public GenericBO<Usuario> getUsuarioBO() {
		return usuarioBO;
	}

	public void setUsuarioBO(GenericBO<Usuario> usuarioBO) {
		this.usuarioBO = usuarioBO;
	}

	
	

}
