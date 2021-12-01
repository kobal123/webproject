package registration;

import java.util.Objects;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cart.CartService;
import form.UserForm;
import form.converter.UserFormConverter;
import user.AppUser;
import user.AppUserService;
import user.RoleDao;
import user.RoleDataAccessService;
import user.RoleService;

@Service
public class RegistrationService {

	private final AppUserService userService;
	private final RoleDataAccessService roleAccessService;
	private final CartService cartService;
	private final PasswordEncoder encoder;
	
	
	
	
	
	public RegistrationService(AppUserService userService, RoleDataAccessService roleService, BCryptPasswordEncoder encoder, CartService cartService) {
		super();
		this.userService = userService;
		this.roleAccessService = roleService;
		this.encoder= encoder;
		this.cartService=cartService;
	}



	/**
	 * 
	 * @param userForm
	 * 
	 * 
	 * Registrate a user from the input UserForm,
	 * add ROLE_USER to the user.
	 * 
	 * 
	 */
	void registerUser(UserForm userForm) {

		
			AppUser user;
		
		
			System.out.println("registrating user....");
		
			UserFormConverter converter = new UserFormConverter();
			
			user = converter.UserFromUserForm(userForm);
			String password  =user.getPassword();
			user.setPassword(encoder.encode(password));
			

			Long userId = userService.addUser(user);
			cartService.createCartForNewUser(userId);
			roleAccessService.addUserRoleToUser(user.getName());

			
		
	}
	
}
