package user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;




public class AppUser{

private  Long id;
private String name;
private String email;
private String password;
private LocalDateTime createdAt;
private List<Role> roles = new ArrayList<>();






public AppUser(Long id, String name, String email, String password, LocalDateTime created) {
	this.id = id;
	this.name = name;
	this.email = email;
	this.password = password;
	this.createdAt = created;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public LocalDateTime getCreatedAt() {
	return createdAt;
}
public void setCreated(LocalDateTime created) {
	this.createdAt = created;
}
public List<Role> getRoles() {
	return roles;
}
public void addRoles(Role role) {
	this.roles.add(role);
}



@Override
public int hashCode() {
	return Objects.hash(createdAt, email, id, name,createdAt);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	AppUser other = (AppUser) obj;
	return Objects.equals(createdAt, other.createdAt) && Objects.equals(email, other.email) && Objects.equals(id, other.id)
			&& Objects.equals(name, other.name);
}


@Override
public String toString() {
	return "AppUser [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", createdAt="
			+ createdAt + ", roles=" + roles.toString() + "]";
}

}
