package search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import product.Product;
import product.ProductDataAccessService;

@Service
public class SearchService {

	@Autowired
	private ProductDataAccessService productDataAccessService;
	
	
	
	
	public List<Product> getProductsWithParameters(String productName,String sortBy,String priceBetween){
		
		
		/*
		 * TODO: handle the parameters
		 */
		
		List<Product> products = productDataAccessService.getProductsByName(productName);
		
		return products;
	}
	
	
	public List<String> searchAutoComplete(String productName){
		
		return productDataAccessService.getProductNamesLikeInput(productName);
	}
	
	
	
	
}
