package cartelera.virtual.controller;

import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

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
import cartelera.virtual.bo.impl.GuaraniBOImpl;
import cartelera.virtual.common.error.ResponseError;
import cartelera.virtual.dto.AlumnoDTO;
import cartelera.virtual.dto.LoginInfoDTO;
import cartelera.virtual.exception.FindException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RequestMapping("/api/loginAuthentication")
@Controller
public class LoginAuthenticationController {
	
	private static final int expirationHours = 1;
	private String secret = "mySecret";
	@Autowired
	private GuaraniBOImpl guaraniBO;


	public LoginAuthenticationController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> authentication(HttpServletRequest request, @RequestBody LoginInfoDTO login) {
		try {
			ResponseEntity<?> re = null;
			String token = null;
			//FIXME - recuperar un usuario genérico, luego validar el rol
			AlumnoDTO loginUser = guaraniBO.alumnoLogin(login.getUsername(), login.getPassword());
			if(loginUser != null && !"".equals(loginUser)){
				//crear jwt-token
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				Claims claims = Jwts.claims().setSubject(login.getUsername());
				claims.put("username", login.getUsername());
				//va la pass?
				claims.put("password", login.getPassword());
				claims.put("created", cal.getTime());
				cal.add(Calendar.HOUR_OF_DAY, expirationHours);
				claims.put("expiration", cal.getTime());
				token = URLEncoder
						.encode(Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact());

			}
			return new ResponseEntity<String>(token, HttpStatus.OK);
		} catch (FindException e) {
			e.printStackTrace();
			ResponseError error = new ResponseError();
			error.setError("LoginAuthenticationController - Ocurrió un error al intentar validar el usuario " + login.getUsername());
			return new ResponseEntity<ResponseError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
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
	
}
