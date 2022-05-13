import java.time.LocalDateTime;

public class ProductComment {

	Long id;
	Long productId;
	String review;
	LocalDateTime createdAt;
	public ProductComment(Long id, Long productId, String review, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.productId = productId;
		this.review = review;
		this.createdAt = createdAt;
	}
	public ProductComment(Long productId, String review, LocalDateTime createdAt) {
		super();
		this.productId = productId;
		this.review = review;
		this.createdAt = createdAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
}
