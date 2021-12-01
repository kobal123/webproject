package cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

	
	
	public IndexController() {
		super();
		System.out.println("index controller");
	}

	@GetMapping("")
	public String index() {
		System.out.println("aaaa");
		return "index";
	}
}
