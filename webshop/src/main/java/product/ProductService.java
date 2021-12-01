package product;

import java.util.List;

import org.springframework.stereotype.Service;

import image.ImageDao;

@Service
public class ProductService {
	private final ProductDao dao;
	private final ImageDao imageDao;

	
	
	
	public ProductService(ProductDao dao, ImageDao imageDao) {
		super();
		this.dao = dao;
		this.imageDao = imageDao;
	}


	public List<String> productNameAutoComplete(String productName){
		dao.getProductNamesLikeInput(productName);
		
		
		return null;
	}
	
	
	public List<Product> getProductsByUserId(Long id){
		return dao.selectProductsByUserId(id);
	}
	
	
	public Long saveProduct(Product p) {
		return dao.addProduct(p);
	}
	
	public Product getProductById(Long id) {
		Product p = dao.getProductById(id);
		p.setImages(imageDao.getProductImageById(id));
		return p;
	}
	
	
	public Product getProductByIdAndName(Long id,String name) {
		return dao.getProductByIdAndName(id, name);
	}
	
	
	
}
