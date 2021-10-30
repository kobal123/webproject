package product;

import java.util.List;

import org.springframework.stereotype.Repository;


public interface ProductDao {
	
	List<Product> selectProducts();
	
	int addProduct(Product p);
	
	
	List<Product> selectProductsByUserId(Long id);
}
