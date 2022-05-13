package order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cart_item.CartItem;
import product.Product;

public class Order{

	private Long id;
	private Long userId;
	private Double grandTotal;
	private String shippingAddress;
	private LocalDate createdAt;
	private LocalDateTime updatedAt;

	
	public Order(Long id, Long userId, Double grandTotal, String shippingAddress, LocalDate createdAt,
			LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.grandTotal = grandTotal;
		this.shippingAddress = shippingAddress;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;

	}
	
	public Order( Long userId, Double grandTotal, String shippingAddress, LocalDate createdAt,
			LocalDateTime updatedAt) {
		super();
		this.userId = userId;
		this.grandTotal = grandTotal;
		this.shippingAddress = shippingAddress;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;

	}
	public Order( Long userId, String shippingAddress, LocalDate createdAt,
			LocalDateTime updatedAt) {
		super();
		this.userId = userId;
		this.grandTotal = grandTotal;
		this.shippingAddress = shippingAddress;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;

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


	public Double getGrandTotal() {
		return grandTotal;
	}


	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}


	public String getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


	public LocalDate getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}


	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
	
	
	
}
