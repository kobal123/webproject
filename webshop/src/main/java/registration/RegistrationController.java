package registration;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import form.UserForm;
import form.converter.UserFormConverter;
import user.AppUser;
import user.AppUserService;

@Controller
@RequestMapping("/signup")
public class RegistrationController {
	private final AppUserService service;
	private final BCryptPasswordEncoder encoder;
	public RegistrationController(AppUserService service, BCryptPasswordEncoder encoder) {
		this.service = service;
		this.encoder=encoder;
	}
	
	
	@GetMapping("")
	public String signupPage(Model m) {
		m.addAttribute("userform", new UserForm());
		return "signup";
	}
	
	
	
	
	
	@PostMapping("")
	public String registerUser(Model m,UserForm p){
		System.out.println(p.toString());
		
		AppUser u = service.getUserByUsername(p.getUsername());
		
		if(Objects.equals(u, null)) {
			System.out.println("registrating user....");
		
			UserFormConverter converter = new UserFormConverter();
			
			u = converter.UserFromUserForm(p);
			String password  =u.getPassword();
			u.setPassword(encoder.encode(password));
			service.addUser(u);
			
			
		}else{
			System.out.println("user already exists, error");
		}
		
		
		return "redirect:/login";
	}
}
