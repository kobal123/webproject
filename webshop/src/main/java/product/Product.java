package product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import image.ProductImage;


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
	private String path;
	private String description;
	List<ProductImage> images = new ArrayList<>();
	private LocalDateTime  createdAt;
	private LocalDateTime  deletedAt;
	
	
	public Product(Long id, Long userId, Double price, Integer quantity,String name) {
		super();
		this.id = id;
		this.userId = userId;
		this.price = price;
		this.Quantity = quantity;
		this.name=name;
		this.path = name.replace(" ", "-") + "-" + id;
		
	}
	public Product(Long userId, Double price, Integer quantity,String name) {
		super();

		this.userId = userId;
		this.price = price;
		this.Quantity = quantity;
		this.name=name;

	}
	
	public Product(Long userId, Double price, Integer quantity,String name,String description) {
		super();

		this.userId = userId;
		this.price = price;
		this.Quantity = quantity;
		this.name=name;
		this.description = description;

	}
	
	
	
	
	
	public Product(Long id, Long userId, Double price, Integer quantity, String name, String path, String description,
			List<ProductImage> images, LocalDateTime createdAt, LocalDateTime deletedAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.price = price;
		Quantity = quantity;
		this.name = name;
		this.path = path;
		this.description = description;
		this.images = images;
		this.createdAt = createdAt;
		this.deletedAt = deletedAt;
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
		this.path = this.name.replace(" ", "-") + "-" + this.id;

	}
	
	public Product(Long id, Long userId,String name, Double price, Integer quantity,String description, LocalDateTime createdAt,
			LocalDateTime deletedAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.price = price;
		Quantity = quantity;
		this.createdAt = createdAt;
		this.deletedAt = deletedAt;
		this.name=name;
		this.path = this.name.replace(" ", "-") + "-" + this.id;
		this.description=description;

	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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



	public String getPath() {
		return path;
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



	public List<ProductImage> getImages() {
		return images;
	}
	public void setImages(List<ProductImage> images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", userId=" + userId + ", price=" + price + ", Quantity=" + Quantity
				+  ", createdAt=" + createdAt + ", deletedAt=" + deletedAt + "]";
	}
}
