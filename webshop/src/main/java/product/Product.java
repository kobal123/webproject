package product;

import java.time.LocalDateTime;


/*
 * 
 * 
 * TODO:   a double rossz ötlet pénz tárolására------ kicserélni Java currency API-ra
 * 
 * 
 * */



public class Product{
	private Long id;
	private Long userId;
	private Double price;
	private Integer Quantity;
	private String name;
	
	private LocalDateTime  createdAt;
	private LocalDateTime  deletedAt;
	
	
	public Product(Long id, Long userId, Double price, Integer quantity) {
		super();
		this.id = id;
		this.userId = userId;
		this.price = price;
		Quantity = quantity;
		
	}
	
	
	public Product(Long id, Long userId,String name, Double price, Integer quantity, LocalDateTime createdAt,
			LocalDateTime deletedAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.price = price;
		Quantity = quantity;
		this.createdAt = createdAt;
		this.deletedAt = deletedAt;
		this.name=name;
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



	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public Integer getQuantity() {
		return Quantity;
	}



	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}







	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}



	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}



	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}



	@Override
	public String toString() {
		return "Product [id=" + id + ", userId=" + userId + ", price=" + price + ", Quantity=" + Quantity
				+  ", createdAt=" + createdAt + ", deletedAt=" + deletedAt + "]";
	}
}
