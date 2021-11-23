package product;

import java.util.List;

import org.springframework.stereotype.Repository;


public interface ProductDao {
	
	List<Product> selectProducts();
	
	Long addProduct(Product p);
	
	
	List<Product> selectProductsByUserId(Long id);
	
	
	Product getProductById(Long id);
	
	
	Product getProductByIdAndName(Long id, String name);
	
	List<Product> getProductsByName(String name);
	
	
	List<String> getProductNamesLikeInput(String name);
}
