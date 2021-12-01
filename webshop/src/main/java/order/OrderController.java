package order;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import order.item.OrderItem;

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
		
		return "redirect:/";
	}

	@GetMapping("")
	public String getOrders(Principal p, Model model) {
		List<OrderObjectWrapper> orderItems = orderService.getAllOrderByUserId(Long.parseLong(p.getName()));
		
		for(OrderObjectWrapper o : orderItems) {
			System.out.println(o.getItems().toString());
		}
		
		model.addAttribute("orderItemMap",orderItems);
		
		return "user-orders";
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
