package user;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import order.OrderDao;
import order.OrderDataAccessService;


@Controller
@RequestMapping("/poo")
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
	
	
	@GetMapping("profile")
	public String currentUser(Principal principal,Model m) {

		AppUser u = userService.loadUserById(Long.parseLong(principal.getName()));
		
		m.addAttribute("Principal", principal.getName());
		return "userprofile.html";
	}

	@GetMapping("user2")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttributes());
    }

	
	@GetMapping("fragment")
	public String frag() {
		return "test_fragments.html";
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
