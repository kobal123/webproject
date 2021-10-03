package user;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
public class AppUserController {
	private final AppUserService userService;
	private final RoleService roleService;
	public AppUserController(AppUserService service, RoleService roleservice) {
		this.userService = service;
		this.roleService= roleservice;
	}
	
	
	
	@GetMapping("users")
	public List<AppUser> Users() {
	
			
		
		return userService.getUsers();
	}
	
	
	@GetMapping("user")
	public Principal currentUser(Principal principal) {

		
		
		return principal;
	}
	
	
	
	
	
	@GetMapping("users/{username}")
	public AppUser userRoles(@PathVariable String username) {
		
		return userService.getUserByUsername(username);
	}
	
	
	
	
	@PostMapping("add")
	public void addUser(@RequestBody AppUser user) {
		System.out.println(user.getName());
		userService.addUser(user);
		
	}
	
}
