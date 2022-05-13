package product.comment;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProductComment {

	Long id;
	Long userId;
	Long productId;
	String review;
	LocalDate createdAt;
	public ProductComment(Long id, Long userId, Long productId, String review, LocalDate createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.review = review;
		this.createdAt = createdAt;
	}
	public ProductComment(Long userId, Long productId, String review, LocalDate createdAt) {
		super();
		this.userId = userId;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "ProductComment [id=" + id + ", userId=" + userId + ", productId=" + productId + ", review=" + review
				+ ", createdAt=" + createdAt + "]";
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
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	
	
	
	
	
}
