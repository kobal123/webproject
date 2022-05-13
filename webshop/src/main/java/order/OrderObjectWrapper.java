package order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import image.ProductImage;
import order.item.OrderItem;
import product.Product;

public class OrderObjectWrapper {
	
	private Long orderId;
	private List<OrderItem> items;
	private Double total;
	LocalDate created_at;
	public OrderObjectWrapper(Long orderId, List<OrderItem> items, Double total, Map<OrderItem, Product> map,LocalDate date) {
		super();
		this.orderId = orderId;
		this.items = items;
		this.total = total;
		this.map = map;
		this.created_at=date;
	}



	private Map<OrderItem,Product> map = new HashMap<>();



	

	public Double getTotal() {
		return total;
	}



	public LocalDate getCreated_at() {
		return created_at;
	}



	public void setCreated_at(LocalDate created_at) {
		this.created_at = created_at;
	}



	public void setTotal(Double total) {
		this.total = total;
	}



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
