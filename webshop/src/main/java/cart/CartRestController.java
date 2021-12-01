package cart;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cart_item.CartItem;
import cart_item.CartItemService;
import image.ImageService;
import product.ProductService;


@RestController
@RequestMapping("/api/cart")
public class CartRestController {

	
	private CartService cartService;
	private CartItemService cartItemService;
	private ProductService productService;
	private ImageService imageService;
	
	String message = "this is a message";
	
	
	
	
	public CartRestController(CartService cartService, CartItemService cartItemService, ProductService productService,
			ImageService imageService) {
		super();
		this.cartService = cartService;
		this.cartItemService = cartItemService;
		this.productService = productService;
		this.imageService = imageService;
	}






	@PostMapping(path = "/add")
	public Map<String,Object> addCartItemToCart(Principal principal,@RequestParam(name = "id",required = true) Long productId,@RequestParam(name = "qnt",required = false) Integer quantity) {

		int qnt=quantity;
		System.out.println("about to add new item..");
		CartItem item = null;
		if(Objects.equals(quantity, null)) {
			qnt=1;
			System.out.println("quantity was null");
			item = cartService.addItemToCart(Long.parseLong(principal.getName()), productId);
		}else
			item = cartService.addItemToCart(Long.parseLong(principal.getName()), productId,quantity);
		
		

		Map<String,Object> map = new HashMap<>();
		map.put("id", productId);
		map.put("qnt_diff", item.getQuantity()+qnt);
		map.put("prc", item.getPrice());
		
		return map;
	}
	
}
























