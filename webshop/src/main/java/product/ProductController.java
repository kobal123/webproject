package product;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import product.comment.ProductComment;
import product.comment.ProductCommentForm;
import product.comment.ProductCommentRepository;
import product.comment.ProductCommentWrapper;
import product.comment.ProductFormConverter;
import user.AppUser;
import user.AppUserService;

@Controller
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

	private final ImageService imageService;
	private final ProductService productService;
	private final AppUserService appUserService;
	private final ProductCommentRepository commentRepository;
	
	
	public ProductController(ImageService imageService, ProductService productService, AppUserService appUserService,
			ProductCommentRepository commentRepository) {
		super();
		this.imageService = imageService;
		this.productService = productService;
		this.appUserService = appUserService;
		this.commentRepository = commentRepository;
	}



	@GetMapping("/user/{username}")
	public String getUserProducts(Model m, @PathVariable String username) {
		
		AppUser user = appUserService.getUserByUsername(username);
		System.out.println("asdf");
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
		
		return "products";
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
		List<ProductComment> comments = commentRepository.getCommentsByProductId(productId);
		
		List<ProductCommentWrapper> wrapper = new ArrayList<>();
		for(ProductComment c : comments) {
			wrapper.add(new ProductCommentWrapper(c, appUserService.loadUserById(c.getUserId())));
		}
		
		m.addAttribute("product",p);
		m.addAttribute("image", p.images.get(0));
		m.addAttribute("productCommentForm", new ProductCommentForm());
		m.addAttribute("comments", wrapper);
		return "product-detail";
	}
	


@PostMapping(path = "/p/{path}")
String AddReviewToProduct(Principal p,ProductCommentForm form,@PathVariable String path, Model m) {
	String[] arr = path.split("-");
	Long productId = Long.parseLong(arr[arr.length-1]);
	Long userId = Long.parseLong(p.getName());
	ProductComment comment= ProductFormConverter.ProductCommentFromProductForm(userId,productId,form);
	comment.setUserId(userId);
	comment.setCreatedAt(LocalDate.now());
	System.out.println(comment.toString());
	commentRepository.addCommentToProduct(comment);
	
	
	return "redirect:/";
}

}



























