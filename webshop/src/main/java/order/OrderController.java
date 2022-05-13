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

import order.detail.OrderDetail;
import order.detail.OrderDetailRepository;
import order.item.OrderItem;
import order.item.OrderItemRepository;

@Controller
@RequestMapping("/orders")
public class OrderController {
	private OrderService orderService;
	private OrderDetailRepository orderDetailRepository;
	private OrderItemRepository itemRepository;

	
	
	
	
	
	
	public OrderController(OrderService orderService, OrderDetailRepository orderDetailRepository,
			OrderItemRepository itemRepository) {
		super();
		this.orderService = orderService;
		this.orderDetailRepository = orderDetailRepository;
		this.itemRepository = itemRepository;
	}

	@GetMapping("/place")
	public String placeOrder(Principal p,OrderDetail orderDetail) {
		orderService.placeOrderForUser(Long.parseLong(p.getName()),orderDetail);
		
		return "redirect:/orders";
	}

	@GetMapping("")
	public String getOrders(Principal p, Model model) {
		List<OrderObjectWrapper> orderItems = orderService.getAllOrderByUserId(Long.parseLong(p.getName()));
		
		for(OrderObjectWrapper o : orderItems) {
			System.out.println(o.getItems().toString());
		}
		
		model.addAttribute("orderItems",orderItems);
		
		//return "user-orders";
		return "user-orders";

	}
	
	
	@GetMapping("/checkout")
	public String checkout(Principal p,Model m) {
		
		m.addAttribute("orderDetail",new OrderDetail());
		
		
		return "checkout";
	}
	
	
	
	
	@GetMapping("/{orderId}")
	public String getSingleOrder(Principal principal,@PathVariable Long orderId,Model m) {
		
		try {
			Order o = orderService.loadOrderByUserIdAndOrderId(Long.parseLong(principal.getName()),orderId);
			OrderDetail detail = orderDetailRepository.getOrderDetailsById(orderId);
			List<OrderObjectWrapper> items = orderService.getAllOrderDataByOrderId(orderId);
			m.addAttribute("detail",detail);
			m.addAttribute("items",items.get(0));
			m.addAttribute("order",o);
			System.out.println(items.size());
			
			
		} catch (NumberFormatException e) {

			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return "order_details";
	}
	
	

	
	
	
	
	
}






