package cartelera.virtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cartelera.virtual.bo.impl.GuaraniBOImpl;
import cartelera.virtual.common.error.ResponseError;
import cartelera.virtual.dto.AlumnoDTO;
import cartelera.virtual.dto.LoginInfoDTO;
import cartelera.virtual.exception.FindException;

@RequestMapping("/api/loginAuthentication")
@Controller
public class LoginAuthenticationController {
	
	@Autowired
	private GuaraniBOImpl guaraniBO;


	public LoginAuthenticationController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> authentication(@RequestBody LoginInfoDTO login) {
		try {
			String token= "";
			//FIXME - recuperar un usuario genérico, luego validar el rol
			AlumnoDTO loginUser = guaraniBO.alumnoLogin(login.getUsername(), login.getPassword());
			if(loginUser != null && !"".equals(loginUser)){
				//crear jwt-token
			}
			return new ResponseEntity<String>(token, HttpStatus.OK);
		} catch (FindException e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
			error.setError("LoginAuthenticationController - Ocurrió un error al intentar validar el usuario " + login.getUsername());
			return new ResponseEntity<ResponseError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
}
