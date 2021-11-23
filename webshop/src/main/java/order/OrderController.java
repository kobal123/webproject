package order;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/orders")
public class OrderController {
	private OrderService orderService;


	
	
	
	
	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}

	
	@GetMapping("/place")
	public String placeOrder(Principal p) {
		orderService.placeOrderForUser(Long.parseLong(p.getName()));
		
		return "index";
	}

	@GetMapping("")
	public String getOrders(Principal p) {
		
		
		return "orders";
	}
	
	
	@GetMapping("/{orderId}")
	public String getSingleOrder(Principal principal,@PathVariable Long orderId) {
		
		try {
			Order o = orderService.loadOrderByUserIdAndOrderId(Long.parseLong(principal.getName()),orderId);
		} catch (NumberFormatException e) {

			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return "orders";
	}
}
