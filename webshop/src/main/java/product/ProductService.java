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
	
	
	
	List<Product> getProductsByUserId(Long id){
		return dao.selectProductsByUserId(id);
	}
	
	
	int saveProduct(Product p) {
		return dao.addProduct(p);
	}
	
}
