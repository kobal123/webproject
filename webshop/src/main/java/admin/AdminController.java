package admin;

import java.util.List;
import java.util.Map;

import org.springframework.boot.web.servlet.server.Session;
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
	private MySessionRepository repository;
	
	
	
	
	
	
	
	
	








	public AdminController(AppUserService appUserService, MySessionRepository repository) {
		super();
		this.appUserService = appUserService;
		this.repository = repository;
	}




	@GetMapping("")
	String getLoggedInUsers(Model m){
		Map<String,Object> map = repository.sessions();
		
		for(var x : map.entrySet()) {
			System.out.println(x.getKey()+"   "+x.getValue());
		}
		m.addAttribute("users", map);

		return "admin";
	}
	

	
	@PostMapping("delete/{sessionId}")
	public String deleteSession(@PathVariable String sessionId) {
		System.out.println("session deleted");
		repository.deleteSessionById(sessionId);
		
		
		return "redirect:/admin";
	}
	
	
}
