package order.item;

import java.util.List;

public interface OrderItemDao {

	
	public List<OrderItem> getOrderItemsByOrderId(Long orderId);
	public OrderItem getOrderItemById(Long orderItemId);
	
	int[] saveMultipleOrderItems(List<OrderItem> items);
}
