package user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AppUserController {
	private final AppUserService userService;
	private final RoleService roleService;
	public AppUserController(AppUserService service, RoleService roleservice) {
		this.userService = service;
		this.roleService= roleservice;
	}
	
	
	
	@GetMapping("/users")
	public List<AppUser> Users() {
	
			
		
		return userService.getUsers();
	}
	
	@GetMapping
	public String greetUser() {
		return "logged in!"; 
	}
	
	
	
	@GetMapping("/users/{username}")
	public AppUser userRoles(@PathVariable String username) {
		
		return userService.getUserByUsername(username);
	}
	
	
	
	
	@PostMapping(path = "/add")
	public void addUser(@RequestBody AppUser user) {
		
		userService.addUser(user);
	}
	
}
