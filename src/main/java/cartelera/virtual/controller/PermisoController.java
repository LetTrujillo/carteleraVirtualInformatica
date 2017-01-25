package cartelera.virtual.controller;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cartelera.virtual.bo.GenericBO;
import cartelera.virtual.bo.impl.UsuarioBOImpl;
import cartelera.virtual.common.error.ResponseError;
import cartelera.virtual.dto.PermisoDTO;
import cartelera.virtual.dto.UsuarioDTO;
import cartelera.virtual.entidades.Permiso;
import cartelera.virtual.entidades.Usuario;
import cartelera.virtual.exception.FindException;
import javassist.NotFoundException;

@RequestMapping("/permiso")
@Controller
public class PermisoController {

	private Mapper mapper;
	
	
//	private PermisoBOImpl permisoBO;
//	
	@Autowired
	private UsuarioBOImpl usuarioBO;
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getAllPermisosByUser(@PathVariable String username) {
		try {	
	
			List<Permiso> permisoList = new ArrayList<Permiso>();
			permisoList = this.getUsuarioBO().getPermisosByUser(username);
			
			List<PermisoDTO> permisosDTO =  new ArrayList<PermisoDTO>();
			
			for (Permiso permiso : permisoList){
				PermisoDTO permisoDTO = mapper.map(permiso, PermisoDTO.class);
				permisosDTO.add(permisoDTO);
			}
			
			return new ResponseEntity<List<PermisoDTO>>(permisosDTO,HttpStatus.OK);
			} catch (FindException e) {
				e.printStackTrace();
				ResponseError error = new ResponseError();
				error.setError("UsuarioController - Ocurrió un error al recuperar los permisos de usuario " );
				return new ResponseEntity<ResponseError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	
	

	public UsuarioBOImpl getUsuarioBO() {
		return usuarioBO;
	}



	public void setUsuarioBO(UsuarioBOImpl usuarioBO) {
		this.usuarioBO = usuarioBO;
	}



	public PermisoController(){
		mapper = new DozerBeanMapper();
	}
}
