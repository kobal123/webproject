package cart_item;

import org.springframework.stereotype.Service;

import cart.Cart;
import product.Product;

@Service
public class CartItemService {
	private CartItemDataAccessService cartItemDataAccessService;

	public CartItemService(CartItemDataAccessService cartItemDataAccessService) {
		super();
		this.cartItemDataAccessService = cartItemDataAccessService;
	}
	
	
	
	
	public void addItemToCart(Cart cart, Product product,Integer quantity) {
		CartItem item = new CartItem(cart.getId(),product.getId(), product.getPrice(),quantity );
		
		cartItemDataAccessService.addCartItemToCart(item);
		
		System.out.println("the item "+item.toString()+" was added to the cart "+ cart.toString());
	}
	
	
	
	
	

}
