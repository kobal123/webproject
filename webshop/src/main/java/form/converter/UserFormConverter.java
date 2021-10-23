package form.converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import form.UserForm;
import user.AppUser;

public class UserFormConverter {

	
	public AppUser UserFromUserForm(UserForm form) {
		String username = form.getUsername();
		String email = form.getEmail();
		String password = form.getPassword();
		
		
		return new AppUser(username,email,password);
	}
}
