package product;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
	private final ProductDao dao;

	public ProductService(ProductDataAccessService dao) {
		super();
		this.dao = dao;
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
		return dao.getProductById(id);
	}
	
	
	public Product getProductByIdAndName(Long id,String name) {
		return dao.getProductByIdAndName(id, name);
	}
	
	
	
}
