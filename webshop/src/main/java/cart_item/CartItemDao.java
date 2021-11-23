package cart_item;

import java.util.List;



public interface CartItemDao {

	public List<CartItem> getCartItemsByCartId(Long id);
	
	public CartItem getSingleCartItemById(Long id);
	
	Long addCartItemToCart(CartItem item);

	public void updateCartItem(Long id, int quantity);
	
	public void deleteCartItemsByCartId(Long cartId);
	public void deleteCartItemById(Long cartItemId);

	
	
}
