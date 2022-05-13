package product.comment;

import java.util.List;

public interface ProductCommentDao {

	
	public List<ProductComment> getCommentsByProductId(Long productId);
}
