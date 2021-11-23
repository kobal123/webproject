package order;

import java.util.List;

public interface OrderDao {

	
	
	List<Order> getOrdersByUserId(Long id);
	
	Order getOrderByOrderId(Long orderId);
	
	Long saveOrder(Order o);
	
	
}
