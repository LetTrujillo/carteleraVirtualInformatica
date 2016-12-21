package cartelera.virtual.controller;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cartelera.virtual.bo.GenericBO;
import cartelera.virtual.common.error.ResponseError;
import cartelera.virtual.dto.PermisoDTO;
import cartelera.virtual.entidades.Cartelera;
import cartelera.virtual.entidades.Permiso;
import cartelera.virtual.entidades.Usuario;
import cartelera.virtual.exception.UpdateException;
import javassist.NotFoundException;

@RestController
public class PermisoController {
	
	@Autowired
	private GenericBO<Usuario> usuarioBO;
	
	@Autowired
	private GenericBO<Cartelera> carteleraBO;
	
	private Mapper mapper;
	
	@RequestMapping(value = "/permiso", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> otorgarPermisos(@RequestBody PermisoDTO permisoDTO) {
		try {
			Usuario usuario = this.getUsuarioBO().find(permisoDTO.getUsuario());
			Cartelera cartelera = this.getCarteleraBO().find(permisoDTO.getCartelera());
			Permiso permiso = new Permiso();
			permiso.setCartelera(cartelera);
			permiso.setUsuario(usuario);
			permiso.setPuede_editar(permisoDTO.isPuede_editar());
			permiso.setPuede_publicar(permisoDTO.isPuede_publicar());
			List<Permiso> permisos = new ArrayList<Permiso>();
			permisos.add(permiso);
			usuario.setPermisos(permisos);
			this.getUsuarioBO().update(usuario);
			
			PermisoDTO retval = mapper.map(permiso, PermisoDTO.class);
			return new ResponseEntity<PermisoDTO>(retval, HttpStatus.OK);
		} catch (UpdateException e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
			error.setError("PermisoController - Ocurrió un error al intentar agregar permisos al usuario " + permisoDTO.getUsuario());
			return new ResponseEntity<ResponseError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
			error.setError("PermisoController - Ocurrió un error al intentar recuperar información para asignar los permisos " + permisoDTO.getUsuario());
			return new ResponseEntity<ResponseError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public GenericBO<Usuario> getUsuarioBO() {
		return usuarioBO;
	}

	public void setUsuarioBO(GenericBO<Usuario> usuarioBO) {
		this.usuarioBO = usuarioBO;
	}

	public GenericBO<Cartelera> getCarteleraBO() {
		return carteleraBO;
	}

	public void setCarteleraBO(GenericBO<Cartelera> carteleraBO) {
		this.carteleraBO = carteleraBO;
	}
		
	public PermisoController(){
		mapper = new DozerBeanMapper();
	}	
}


