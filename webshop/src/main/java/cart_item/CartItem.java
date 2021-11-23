package cart_item;

import java.time.LocalDateTime;



/**
 * aaaaa
 */
public class CartItem {

	private Long id;
	private Long cartId;
	private Long productId;
	private Double price;
	private Integer quantity;
	private LocalDateTime createdAt = null;
	private LocalDateTime updatedAt	=null;
	private String productImagePath;
	
	
	
	
	
	
	
	
	
	public String getProductImagePath() {
		return productImagePath;
	}
	public void setProductImagePath(String productImagePath) {
		this.productImagePath = productImagePath;
	}
	public CartItem(Long cartId,Long productId, Double price, Integer quantity) {
		super();
		this.cartId = cartId;
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
	}
	public CartItem(Long id, Long cartId, Long productId, Integer quantity, Double price,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.cartId = cartId;
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
	@Override
	public String toString() {
		return "CartItem [id=" + id + ", cartId=" + cartId + ", productId=" + productId + ", price=" + price
				+ ", quantity=" + quantity + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	
	
	
	
	
}
