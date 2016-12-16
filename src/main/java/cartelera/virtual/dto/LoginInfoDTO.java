package cartelera.virtual.dto;

import java.io.Serializable;

public class LoginInfoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3860863180875099751L;

	private String username;
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
}
