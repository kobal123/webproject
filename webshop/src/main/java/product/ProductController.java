package product;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import image.ImageDao;
import image.ImageService;
import image.ProductImage;
import user.AppUser;
import user.AppUserService;

@Controller
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

	private final ImageService imageService;
	private final ProductService productService;
	private final AppUserService appUserService;
	
	
	
	public ProductController(ImageService imageService, ProductService productService, AppUserService appUserService) {
		super();
		this.imageService = imageService;
		this.productService = productService;
		this.appUserService = appUserService;
	}
	
	@GetMapping("/{username}")
	public String getUserProducts(Model m, @PathVariable String username) {
		
		AppUser user = appUserService.getUserByUsername(username);
		
		List<Product> products = productService.getProductsByUserId(user.getId());
		
		for( Product p : products) {
			p.setImages( imageService.getImageByProductId(p.getId()));
		}
		
		m.addAttribute("products", products);
		
		return "user-products";
	}
	
	

	@GetMapping("add")
	public String addPage(Model model) {

		model.addAttribute("productForm",new ProductForm());
		return "add-product";
	}
	
	@GetMapping("")
	public String indexPage(Principal p, Model m) {
		AppUser user = appUserService.loadUserById(Long.parseLong(p.getName()));
		
		List<Product> products = productService.getProductsByUserId(user.getId());
		
		for( Product product : products) {
			product.setImages( imageService.getImageByProductId(product.getId()));
		}
		
		m.addAttribute("products", products);
		
		return "user-products";
	}
	
	
	@GetMapping(path = "/{product_id}/{image_name}",
			produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	byte[] loadProductImage(@PathVariable Long product_id, @PathVariable String image_name) {
		Product p = productService.getProductById(product_id);
		p.images = imageService.getImageByImageNameAndProductId(product_id, image_name);
		return p.images.get(0).getData();
	}
	
	
	@RequestMapping(
			path = "add",
			method = RequestMethod.POST,
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE
			)
	public String createProduct(ProductForm pform, @RequestParam(name = "file") MultipartFile file,Principal principal) {
		
		System.out.println(file.getOriginalFilename()+"   "+ file.getSize()+" "+ file.getContentType());
		ProductFormAdapter adapter = new ProductFormAdapter();
		Product p = adapter.ProductFromProductForm(pform);
		p.setUserId(Long.parseLong(principal.getName()));
		p.setCreatedAt(LocalDateTime.now());
		
		
		try {
			Long id = productService.saveProduct(p);
			imageService.saveImage(new ProductImage(id,file.getOriginalFilename(),file.getBytes()));
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return "redirect:/products";
}
	
	
	@GetMapping(path = "/p/{path}")
	public String singleProductInformation(@PathVariable String path, Model m) {
		String[] arr = path.split("-");
		Long productId = Long.parseLong(arr[arr.length-1]);
		StringBuilder builder = new StringBuilder();
		
		
		for(int i=0;i<arr.length-1;i++) {
			builder.append(arr[i]);
			if(i!=arr.length-2)
				builder.append(" ");
		}
		String name = builder.toString();
		Product p = productService.getProductByIdAndName(productId, name);
		p.images = imageService.getImageByProductId(productId);
		
		m.addAttribute("product",p);
		m.addAttribute("image", p.images.get(0));
		
		
		return "product-detail";
	}
	
}



