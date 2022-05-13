package product.comment;

import user.AppUser;

public class ProductCommentWrapper {

	
	private final ProductComment comment;
	private final AppUser user;
	public ProductCommentWrapper(ProductComment comment, AppUser user) {
		super();
		this.comment = comment;
		this.user = user;
	}
	public ProductComment getComment() {
		return comment;
	}
	public AppUser getUser() {
		return user;
	}
	
	
}
