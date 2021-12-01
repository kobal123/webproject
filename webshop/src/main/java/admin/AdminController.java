package admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import user.AppUser;
import user.AppUserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private AppUserService appUserService;
	private SessionService sessionService;
	
	
	
	
	
	
	
	
	public AdminController(AppUserService appUserService) {
		super();
		this.appUserService = appUserService;
	}








	@GetMapping("")
	String getLoggedInUsers(Model m){
		
		m.addAttribute("users", appUserService.getUsers());
		
		return "admin";
	}
	
	
	
	
	@PostMapping(path = "/sessions/delete/{userId}")
	void invalidateUserSession(@PathVariable Long userId) {
		
		
		
		
	}
	
	
	
}
