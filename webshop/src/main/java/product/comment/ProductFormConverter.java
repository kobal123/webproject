package product.comment;

public class ProductFormConverter {

	public static ProductComment ProductCommentFromProductForm(Long userId,Long productId,ProductCommentForm form) {
		
		
		return new ProductComment(userId,productId,form.getReview(),form.getCreatedAt());
	}
	
	
}
