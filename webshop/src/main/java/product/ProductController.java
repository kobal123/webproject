package product;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

	
	
	@GetMapping("/add")
	public String addPage() {
		return "custom";
	}
	
	@GetMapping("/")
	public String indexPage() {
		return "custom";
	}
	
	
	
	
	@PostMapping("/add")
	public String createProduct() {
		
		
		
		return "index";
	}
}
