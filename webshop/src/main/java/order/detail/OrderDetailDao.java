package order.detail;

public interface OrderDetailDao {

	
	
	
	public OrderDetail getOrderDetailsById(Long orderId);
	public void saveOrderDetail(Long orderId,OrderDetail detail);
}
