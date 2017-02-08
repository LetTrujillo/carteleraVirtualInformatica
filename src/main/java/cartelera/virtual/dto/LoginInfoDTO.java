package cartelera.virtual.dto;

import java.io.Serializable;

public class LoginInfoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3860863180875099751L;

	private String username;
	private String token;
	private String role;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
