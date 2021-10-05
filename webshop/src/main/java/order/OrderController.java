package order;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
	private final OrderDao dao;
	
	public OrderController(OrderDataAccessService service) {
		this.dao = service;
	}
	
	
	
	@PostMapping("new")
	public void makeOrder(){
		
	}
	
	
	
	
	@GetMapping("/")
	public List<Order> getOrders(Principal p) {
		List<Order> o = dao.getOrdersByUserId(Long.parseLong(p.getName()));
		
		
		return o;
	}
	
	
	public String getOrderById() {
		
		Order o = dao.getOrderByOrderId();
		
		return "";
	}
}
