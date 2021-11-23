package cart;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cart_item.CartItem;
import cart_item.CartItemDataAccessService;
import cart_item.CartItemService;
import image.ImageService;
import product.Product;
import product.ProductService;

@Controller
@RequestMapping("cart")
public class CartController {
	private CartService cartService;
	private CartItemService cartItemService;
	private ProductService productService;
	private ImageService imageService;









	public CartController(CartService cartService, CartItemService cartItemService, ProductService productService,
			ImageService imageService) {
		super();
		this.cartService = cartService;
		this.cartItemService = cartItemService;
		this.productService = productService;
		this.imageService = imageService;
	}




	@GetMapping("")
	private String getUserCart(Principal principal, Model model) {
		Cart shoppingCart = cartService.getUserShoppingCartByUserId(Long.parseLong(principal.getName()));
		List<CartObjectWrapper> wrapper = new ArrayList<>();
		for(var item : shoppingCart.getItems()) {
			Product p = productService.getProductById(item.getProductId());
			p.setImages(imageService.getImageByProductId(p.getId()));
			wrapper.add( new CartObjectWrapper(item, p));
		}
		
		model.addAttribute("wrapper",wrapper);
		model.addAttribute("total_price", "this is a message");
		return  "cart";
	}
	
	
	
	
	@PostMapping("/add")
	public String addCartItemToCart(Principal principal,@RequestParam(name = "id",required = true) Long productId,@RequestParam(name = "qnt",required = false) Integer quantity) {


		System.out.println("about to add new item..");
		if(Objects.equals(quantity, null)) {
			System.out.println("quantity was null");
			cartService.addItemToCart(Long.parseLong(principal.getName()), productId);
		}else
			cartService.addItemToCart(Long.parseLong(principal.getName()), productId,quantity);
		
		return "redirect:/cart";
	}
	
	
	
}





















