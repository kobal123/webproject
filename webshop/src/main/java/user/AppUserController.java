package user;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import order.OrderDao;
import order.OrderDataAccessService;


@RestController
@RequestMapping("/api")
public class AppUserController {
	private final AppUserService userService;
	private final RoleService roleService;
	private final OrderDao dao;

	public AppUserController(AppUserService service, RoleService roleservice,
			OrderDataAccessService orderservice) {
		this.userService = service;
		this.roleService= roleservice;
		this.dao = orderservice;
	}
	
	
	
	@GetMapping("users")
	public List<AppUser> Users() {
	
			
		
		return userService.getUsers();
	}
	
	
	@GetMapping("user")
	public String currentUser(Principal principal) {

		
		
		return principal.getName();
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
	
	@GetMapping("add/{id}")
	public String test(@PathVariable int id,Principal p){
		AppUser u = userService.getUserByUsername(p.getName());
		
		return u.toString();
	}
	
}
