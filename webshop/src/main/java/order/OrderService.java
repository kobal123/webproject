package order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cart.Cart;
import cart.CartService;
import cart_item.CartItem;
import order.item.OrderItem;
import order.item.OrderItemDao;




@Service
public class OrderService {
	private final OrderDao orderDao;
	private final CartService cartService;
	private final OrderItemDao orderItemDao;

	

	
	
	
	public OrderService(OrderDao dataAccessService, CartService cartService, OrderItemDao orderItemDao) {
		super();
		this.orderDao = dataAccessService;
		this.cartService = cartService;
		this.orderItemDao = orderItemDao;
	}






	/**
	 * Loads a single order, checks if the order is 
	 * associated with the requesting user.
	 * 
	 * 
	 * @param userId Owner of the order
	 * @param orderId The Id of the order we want.
	 * @return
	 * @throws Exception
	 */
	public Order loadOrderByUserIdAndOrderId(long userId, Long orderId) throws Exception {

		Order o = orderDao.getOrderByOrderId(orderId);
		if(!Objects.equals(o, null) &&!Objects.equals(userId, orderId)) {
			throw new Exception("user is trying to access an order that is not theirs");
		}
		
		return o ;
	}
	
	
	/**
	 * Places an order for the given user. The order will consist of the items in
	 * users current shopping cart. All current cart items will be deleted.
	 * 
	 * 
	 * @param userId
	 */
	@Transactional
	public void placeOrderForUser(Long userId) {
		Cart cart = cartService.getUserShoppingCartByUserId(userId);
		
		List<OrderItem> orderItems = OrderItemsFromCartItems(cart);
		Double grandTotal = orderItems.stream().mapToDouble(OrderItem::getGrandTotal).sum();
		Order order = new Order(userId,grandTotal,"Hungary, Újkenéz, Petőfi u. 63",LocalDateTime.now(),null);
		Long orderId = orderDao.saveOrder(order);
		cartService.deleteCartItemsByCartId(cart.getId());
		orderItems.stream().forEach(o-> o.setOrderId(orderId));;
		orderItemDao.saveMultipleOrderItems(orderItems);

	}
	
	
	/**
	 * 
	 * 
	 * @param cart
	 * @return
	 * 
	 */
	private  List<OrderItem> OrderItemsFromCartItems(Cart cart ) {

		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for (CartItem item : cart.getItems()) {
			orderItems.add(new OrderItem(item.getProductId(),item.getPrice()*item.getQuantity(), item.getQuantity()));
		}
		
		
		return orderItems;
	}
	
	
	
	
	
	
}
