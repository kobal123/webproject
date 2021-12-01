package cart;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cart_item.CartItem;

public class Cart {

	private Long id;
	private Long userId;
	private  LocalDateTime createdAt;
	private LocalDateTime updatedAt=null;
	private List<CartItem> items = new ArrayList<>();
	
	
	public Cart(Long id, Long userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	
	public Cart(Long userId) {
		super();
		this.userId = userId;
		this.createdAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	public void addCartItem(CartItem item) {
		this.items.add(item);
	}
	
	
	public void setCartItems(List<CartItem> items) {
		this.items = items;
	}


	public List<CartItem> getItems() {
		return items;
	}
	
	
	
}
