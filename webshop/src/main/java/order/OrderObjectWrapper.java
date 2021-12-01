package order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import image.ProductImage;
import order.item.OrderItem;
import product.Product;

public class OrderObjectWrapper {
	
	private Long orderId;
	private List<OrderItem> items;
	
	private Map<OrderItem,Product> map = new HashMap<>();





	public Map<OrderItem, Product> getMap() {
		return map;
	}



	public void setMap(Map<OrderItem, Product> map) {
		this.map = map;
	}






	public OrderObjectWrapper(Long orderId, List<OrderItem> items, Map<OrderItem, Product> map) {
		super();
		this.orderId = orderId;
		this.items = items;
		this.map = map;
	}



	public OrderObjectWrapper(Long orderId, List<OrderItem> items) {
		super();
		this.orderId = orderId;
		this.items = items;
	}



	public Long getOrderId() {
		return orderId;
	}



	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}



	public List<OrderItem> getItems() {
		return items;
	}



	public void setItems(List<OrderItem> items) {
		this.items = items;
	}




	
	
	
	
}
