package order;

import java.time.LocalDateTime;
import java.util.List;

import product.Product;

public class Order{

	private Long id;
	private Long userId;
	private Double grandTotal;
	private String shippingAddress;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Boolean cancelled;
	/*
	 * TODO: a rendelés státuszát valahogy menteni:
	 * 		pl: már feladták, kézbesítették stb
	 * 
	 * 
	 * 
	 * */
	public Order(Long id, Long userId, Double grandTotal, String shippingAddress, LocalDateTime createdAt,
			LocalDateTime updatedAt, Boolean cancelled) {
		super();
		this.id = id;
		this.userId = userId;
		this.grandTotal = grandTotal;
		this.shippingAddress = shippingAddress;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.cancelled = cancelled;
	}
	
	
	
	
}
