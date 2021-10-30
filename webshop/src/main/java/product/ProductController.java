package product;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import image.ImageDao;
import image.ImageService;

@Controller
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

	private final ImageService imageService;
	private final ProductService productService;
	
	
	
	
	public ProductController(ImageService imageService, ProductService productService) {
		super();
		this.imageService = imageService;
		this.productService = productService;
	}

	@GetMapping("add")
	public String addPage(Model model) {

		model.addAttribute("productForm",new ProductForm());

		return "product";
	}
	
	@GetMapping("")
	public String indexPage(Principal p, Model m) {
		
		m.addAttribute("products", productService.getProductsByUserId(Long.parseLong(p.getName())));
		
		return "custom";
	}
	
	
	@GetMapping("/{id}/img")
	byte[] loadProductImage(@PathVariable Long id) {
		
		
		return imageService.getImageByProductId(id);
	}
	
	
	@RequestMapping(
			path = "add",
			method = RequestMethod.POST,
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE
			)
	public void createProduct(ProductForm pform,@RequestParam(name = "file") MultipartFile file,Principal principal) {
		System.out.println(file.getOriginalFilename()+"   "+ file.getSize()+" "+ file.getContentType());
		ProductFormAdapter adapter = new ProductFormAdapter();
		Product p = adapter.ProductFromProductForm(pform);
		p.setUserId(Long.parseLong(principal.getName()));
		p.setCreatedAt(LocalDateTime.now());

		/*
		 * TODO:
		 * 
		 * 	1.kép mentése-> ehhez kell a userId -> principal.getName(),
		 *  valamint a product formból ki kell nyerni a product információit, menteni kell azt. 
		 *	  valamint be kell szúrni az adatbázisba a product, productimages valamint az images
		 *	  táblába az adatokat.	
		 * 
		 * 
		 * 	2. valahogy kezelni kell azt az esetet, amikor egy producthoz több kép jár
		 *	   ,ez inkább thymeleafben lesz probléma.
		 *
		 *		
		 * */
		
		System.out.println("product is being saved...");
		System.out.println(p.toString());
		productService.saveProduct(p);
		
		
		}
	
}






















