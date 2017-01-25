package cartelera.virtual.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cartelera.virtual.bo.UsuarioBO;
import cartelera.virtual.bo.impl.GuaraniBOImpl;
import cartelera.virtual.common.error.ResponseError;
import cartelera.virtual.dto.AlumnoDTO;
import cartelera.virtual.dto.DocenteDTO;
import cartelera.virtual.dto.LoginInfoDTO;
import cartelera.virtual.dto.UsuarioDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RequestMapping("/authentication")
@Controller
public class AuthenticationController {

	private static final int expirationHours = 1;
	private String secret = "mySecret";
	@Autowired
	private GuaraniBOImpl guaraniBO;
	@Autowired
	private UsuarioBO usuarioBO;

	public AuthenticationController() {
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> authentication(HttpServletRequest request, @RequestBody LoginInfoDTO login) {
		try {
			String token = null;
			UsuarioDTO loginUser = usuarioBO.login(login.getUsername(), login.getPassword());

			if(loginUser != null && !"".equals(loginUser)){
				//crear jwt-token
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				Claims claims = Jwts.claims().setSubject(login.getUsername());
				claims.put("created", cal.getTime());
				cal.add(Calendar.HOUR_OF_DAY, expirationHours);
				claims.put("expiration", cal.getTime());

				if(loginUser.getPerfil().equals("ALUMNO")){
					AlumnoDTO guaraniAlumno = guaraniBO.alumnoLogin(login.getUsername(), login.getPassword());
					claims.put("username", guaraniAlumno.getNombreUsuario());	//Tomamos el nombre de usuario de Guaraní
					claims.put("password", login.getPassword());
				}else if(loginUser.getPerfil().equals("DOCENTE")){
					DocenteDTO guaraniDocente = guaraniBO.docenteLogin(login.getUsername(), login.getPassword());
					claims.put("username", guaraniDocente.getNombreUsuario());	//Tomamos el nombre de usuario de Guaraní
					claims.put("password", login.getPassword());
				}else{
					claims.put("username", login.getUsername());
					claims.put("password", login.getPassword());
				}
				token = URLEncoder
						.encode(Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact());
			}

			return new ResponseEntity<String>(token, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
			error.setError("LoginAuthenticationController - Ocurrió un error al intentar validar el usuario " + login.getUsername());
			return new ResponseEntity<ResponseError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> validateToken(HttpServletRequest request) 
			throws IOException, ServletException {
		if(request.getRequestURI().indexOf("/api") != -1 ){
			String token = request.getHeader("Authorization");
			request.getHeaderNames();
			Date now = new Date();
			Claims claims = getClaimsFromToken(token);
			if (claims != null) {
				UsuarioDTO currentUser = null;
				try {
					String username = (String)claims.get("username");
					String password = (String)claims.get("password");
					
					currentUser = usuarioBO.login(username, password);
				} catch (Exception e) {
					// TODO: mejorar el manejo de errores y hacerlo global quizas
					e.printStackTrace();
				}
				
				if(currentUser != null){
					Long expiration = (Long) claims.get("expiration");
					if (expiration >= now.getTime()) {
						//Datos válidos
						return new ResponseEntity<Boolean>(true, HttpStatus.OK);
					} else {
						return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);				
					}
				}else{
					return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);

				}
			} else {
				return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
			}			
		}else{
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
	}


	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	public UsuarioBO getUsuarioBO() {
		return usuarioBO;
	}

	public void setUsuarioBO(UsuarioBO usuarioBO) {
		this.usuarioBO = usuarioBO;
	}

}
