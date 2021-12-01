package cart;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import cart_item.CartItem;
import cart_item.CartItemDao;
import product.Product;
import product.ProductDataAccessService;

@Service
public class CartService {
	private CartDao accessService;
	private CartItemDao cartItemDao;
	private ProductDataAccessService productDataAccessService;
	
	
	



	public CartService(CartDao accessService, CartItemDao cartItemDao,
			ProductDataAccessService productDataAccessService) {
		super();
		this.accessService = accessService;
		this.cartItemDao = cartItemDao;
		this.productDataAccessService = productDataAccessService;
	}


	/**
	 * 
	 * Loads a user's shopping cart with all the items put in it.
	 * 
	 * 
	 * @param userId
	 * The id of the currently logged in user.
	 * @return 
	 */
	public Cart getUserShoppingCartByUserId(long userId) {
		Cart cart = accessService.getCartByUserId(userId);
		cart.setCartItems( cartItemDao.getCartItemsByCartId(cart.getId()));
		return cart;
	}
	
	
	/**
	 * Creates a new Cart entity in the database.
	 * Should only be used when registrating a new user
	 * 
	 * @param userId
	 */
	public void createCartForNewUser(Long userId) {
		accessService.createCartForNewUserById(userId);
	}
	
	public void deleteCartItemById(Long cartItemId) {
		cartItemDao.deleteCartItemById(cartItemId);
	}
	
	
	
	public CartItem addItemToCart(Long userId,Long productId) {
		Long start = System.currentTimeMillis();
		Cart cart = accessService.getCartByUserId(userId);
		Product p = productDataAccessService.getProductById(productId);
		
		//CartItem item = new CartItem(cart.getId(),productId,p.getPrice(),1);
		CartItem item = CheckItem(cart, productId);
		
		if(!Objects.equals(item, null)) {
			System.out.println("The item " + item.toString()+ " exist, increasing quantity");
			cartItemDao.updateCartItem(productId,item.getQuantity()+1);
		}else {
			System.out.println("The item   does not exist yet, adding it");
			item = new CartItem(cart.getId(),productId,p.getPrice(),1);
			cartItemDao.addCartItemToCart(item);
		}
		
		Long end = System.currentTimeMillis();
		System.out.println("inserting into a cart took "+ (end-start)/1000 +" seconds");
		
		return item;
	}
	
	public CartItem addItemToCart(Long userId,Long productId,Integer quantity) {
		if(quantity <1);// TODO: check if the quantity is less than 0. If it is, throw exception
		
		Long start = System.currentTimeMillis();
		Cart cart = accessService.getCartByUserId(userId);
		Product p = productDataAccessService.getProductById(productId);
		
		//CartItem item = new CartItem(cart.getId(),productId,p.getPrice(),1);
		CartItem item = CheckItem(cart, productId);
		
		if(!Objects.equals(item, null)) {
			System.out.println("The item " + item.toString()+ " exist, increasing quantity by "+quantity);
			cartItemDao.updateCartItem(productId,item.getQuantity()+quantity);
		}else {
			System.out.println("The item  does not exist yet, adding it");
			item = new CartItem(cart.getId(),productId,p.getPrice(),1);
			cartItemDao.addCartItemToCart(item);
		}
		
		Long end = System.currentTimeMillis();
		System.out.println("inserting into a cart took "+ (end-start)/1000 +" seconds");
		return item;
		
	}
	
	
	private CartItem CheckItem(Cart cart, Long productId ) {
		List<CartItem> items = cartItemDao.getCartItemsByCartId(cart.getId());

		
		CartItem temp = items.stream()
		.filter(i -> i.getProductId().equals(productId))
		.findFirst()
		.orElse(null);
		
		return temp;
	}
	
	
	public void deleteCartItemsByCartId(Long cartId) {
		cartItemDao.deleteCartItemsByCartId(cartId);
	}
	

}










