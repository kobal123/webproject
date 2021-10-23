package product;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController {

	
	
	@GetMapping("/add")
	public ModelAndView addPage() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("product.html");
	    return modelAndView;
		
	}
	
	@GetMapping("/")
	public String indexPage() {
		return "custom";
	}
	
	
	
	
	@PostMapping("/add")
	public String createProduct() {
		
		
		
		return "index.html";
	}
}
