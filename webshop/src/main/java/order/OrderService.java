package order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cart.Cart;
import cart.CartService;
import cart_item.CartItem;
import image.ImageDao;
import order.detail.OrderDetail;
import order.detail.OrderDetailRepository;
import order.item.OrderItem;
import order.item.OrderItemDao;
import product.Product;
import product.ProductService;




@Service
public class OrderService {
	private final OrderDao orderDao;
	private final CartService cartService;
	private final OrderItemDao orderItemDao;
	private final ProductService productService;
	private final ImageDao imageDao;
	private final OrderDetailRepository detailRepository;

	

	
	
	










	public OrderService(OrderDao orderDao, CartService cartService, OrderItemDao orderItemDao,
			ProductService productService, ImageDao imageDao, OrderDetailRepository detailRepository) {
		super();
		this.orderDao = orderDao;
		this.cartService = cartService;
		this.orderItemDao = orderItemDao;
		this.productService = productService;
		this.imageDao = imageDao;
		this.detailRepository = detailRepository;
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
		
		if(!Objects.equals(o, null) &&!Objects.equals(userId, o.getUserId())) {
			throw new Exception("user is trying to access an order that is not theirs");
		}
		
		return o ;
	}
	
	
public List<OrderObjectWrapper> getAllOrderDataByOrderId(Long orderId){
		
		Order orders = orderDao.getOrderByOrderId(orderId);
		
		List<OrderObjectWrapper> orderItems = new ArrayList<OrderObjectWrapper>();
		
			List<OrderItem> i = orderItemDao.getOrderItemsByOrderId(orderId);
			Map<OrderItem,Product> map = new HashMap<>();
			for(OrderItem tmp : i) {
				Product p = productService.getProductById(tmp.getProductId());
				
				map.put(tmp, p);
			}
			orderItems.add(new OrderObjectWrapper(orderId,i ,orders.getGrandTotal(),map,orders.getCreatedAt()));		
		
		

		return orderItems;
	}
	
	
	
	
	public List<OrderObjectWrapper> getAllOrderByUserId(Long userId){
		
		List<Order> orders = orderDao.getOrdersByUserId(userId);
		
		List<OrderObjectWrapper> orderItems = new ArrayList<OrderObjectWrapper>();
		for(Order o: orders) {
			Long orderId = o.getId();
			List<OrderItem> i = orderItemDao.getOrderItemsByOrderId(o.getId());
			Map<OrderItem,Product> map = new HashMap<>();
			for(OrderItem tmp : i) {
				Product p = productService.getProductById(tmp.getProductId());
				
				map.put(tmp, p);
			}
			orderItems.add(new OrderObjectWrapper(orderId,i ,o.getGrandTotal(),map,o.getCreatedAt()));		
		}
		

		return orderItems;
	}
	
	
	/**
	 * Places an order for the given user. The order will consist of the items in
	 * users current shopping cart. All current cart items will be deleted!
	 * 
	 * 
	 * @param userId
	 */
	@Transactional
	public void placeOrderForUser(Long userId,OrderDetail orderDetail) {
		Cart cart = cartService.getUserShoppingCartByUserId(userId);
		if(cart.getItems().size()==0) {
			System.out.println("cart is empty");
			return;
		}
		
		List<OrderItem> orderItems = OrderItemsFromCartItems(cart);
		
		Double grandTotal =0d;//= orderItems.stream().mapToDouble(OrderItem::getGrandTotal).sum();
		
		for( OrderItem i : orderItems) {
			grandTotal += i.getGrandTotal();
		}
		
		
		Order order = new Order(userId,grandTotal,"Hungary, Újkenéz, Petőfi u. 63",LocalDate.now(),null);
		Long orderId = orderDao.saveOrder(order);
		orderDetail.setOrderId(orderId);
		detailRepository.saveOrderDetail(orderId, orderDetail);
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
			orderItems.add(new OrderItem(item.getProductId(),item.getPrice(), item.getQuantity()));
		}
		
		
		return orderItems;
	}
	
	
	
	
	
	
}
