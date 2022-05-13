package order;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/order")
@CrossOrigin("*")
public class OrderRestController {

	
	
	@GetMapping("")
	public Map<String,String> orders() {
		
		return Collections.singletonMap("Data", "asdf")	;
	};
}
