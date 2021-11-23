package cart;

import cart_item.CartItem;
import product.Product;

/**
 * 
 * @author Kósa Balázs
 *
 * A wrapper class to use for the user's cart.
 * It wraps the CartItem and Product classes
 *
 */
public class CartObjectWrapper {
	private CartItem cartItem;
	private Product product;
	private Double price;
	
	public CartObjectWrapper(CartItem item, Product product) {
		super();
		this.cartItem = item;
		this.product = product;
		this.price = item.getQuantity() * product.getPrice();
	}

	public CartItem getCartItem() {
		return cartItem;
	}

	public Product getProduct() {
		return product;
	}

	public Double getPrice() {
		return price;
	}
	
	
	
	

}
