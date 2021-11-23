package search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import image.ImageService;
import product.Product;
import product.ProductService;

@Controller
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private SearchService searchService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private ProductService productService;
	
	
	
	
	@GetMapping("")
	public String searchProducts(Model model, 
			@RequestParam(required = true,name = "name") String productName,
			@RequestParam(required = false,name="sort") String sortBy,
			@RequestParam(required = false,name = "pbw") String priceBetween) {
		
		
		List<Product> products = searchService.getProductsWithParameters(productName, sortBy, priceBetween);
		for( Product p : products) {
			p.setImages( imageService.getImageByProductId(p.getId()));
		}
		
		model.addAttribute("products", products);

		return "search";
	}
	
	
	
	
	
}
