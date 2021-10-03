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
	 * */
	
}
