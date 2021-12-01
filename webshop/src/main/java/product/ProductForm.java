package product;

import java.time.LocalDateTime;

public class ProductForm {
	private Long id;
	private Long userId;
	private Double price;
	private Integer Quantity;
	private String name;
	private String description;
	private LocalDateTime  updatedAt;
	
	
	
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ProductForm(Long userId, Double price, Integer quantity, String name, String description) {
		super();
		this.userId = userId;
		this.price = price;
		Quantity = quantity;
		this.name = name;
		this.description = description;
	}
	public ProductForm( Long userId,String name, Double price, int quantity) {
		super();
		this.userId=userId;
		this.price = price;
		Quantity = quantity;
		this.name = name;


	}
	public ProductForm( String name, Double price, int quantity) {
		super();
		this.price = price;
		Quantity = quantity;
		this.name = name;


	}
	public ProductForm() {

	}
	
	
	public Long getId() {
		return id;
	}
	public Long getUserId() {
		return userId;
	}
	public Double getPrice() {
		return price;
	}
	public Integer getQuantity() {
		return Quantity;
	}
	public String getName() {
		return name;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	@Override
	public String toString() {
		return "ProductForm [id=" + id + ", userId=" + userId + ", price=" + price + ", Quantity=" + Quantity
				+ ", name=" + name + ", updatedAt=" + updatedAt + "]";
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
	
	
	
	
}
