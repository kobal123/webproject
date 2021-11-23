package user;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;


public class Role implements Serializable {
	private String username;
	private String name;
	
	
	public Role(String username, String rolename) {
		this.username = username;
		this.name = rolename;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "[ name = " + name + " ]";
	}

	
	
	
}