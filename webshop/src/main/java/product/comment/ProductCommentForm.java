package product.comment;

import java.time.LocalDate;

public class ProductCommentForm {

	
	
	

	String review;
	LocalDate createdAt;
	
	
	
	
	
	
	
	public ProductCommentForm() {
		super();
	}

	
	public ProductCommentForm(String review) {
		super();
		this.review = review;
	}

	public ProductCommentForm(String review, LocalDate createdAt) {
		super();
		this.review = review;
		this.createdAt = createdAt;
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
