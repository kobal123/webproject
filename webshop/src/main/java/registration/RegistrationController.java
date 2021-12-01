package registration;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import form.UserForm;
import form.converter.UserFormConverter;
import user.AppUser;
import user.AppUserService;

@Controller
@RequestMapping("/signup")
public class RegistrationController {
	private final RegistrationService registrationService;
	
	
	
	
	public RegistrationController(RegistrationService registrationService) {
		super();
		this.registrationService = registrationService;
	}





	@GetMapping("")
	public String signupPage(Model m) {
		m.addAttribute("userform", new UserForm());
		return "signup";
	}
	
	
	
	
	
	@PostMapping("")
	public String registerUser(UserForm p,Model m){

			
		
		try {
			registrationService.registerUser(p);
		} catch (DataIntegrityViolationException e) {
			if(e instanceof DuplicateKeyException) {
			    System.out.println(e.toString());

				String text = e.toString();
				Pattern word = Pattern.compile("\"unique_.*\"");
				Matcher match = word.matcher(text);
				boolean found=false;
				while (match.find() && !found) {
				     System.out.println("Found love at index "+ match.start() +" - "+ (match.end()-1));
				     System.out.println(e.toString().substring(match.start(),match.end()-1));
				     found=true;
				}
				String message  =e.toString().substring(match.start()+8,match.end()-1);
				System.out.println(message);
				System.out.println(message+" is already taken");
				
				m.addAttribute("userform", p);


			}
		}
			
		return "redirect:/login";
	}
	
}
