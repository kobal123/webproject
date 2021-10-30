package product;

public class ProductFormAdapter {

	
	
	Product ProductFromProductForm(ProductForm p) {
		Long userId = p.getUserId();
		String name = p.getName();
		Double price = p.getPrice();
		Integer quant = p.getQuantity();
		
		
		return new Product(userId,price,quant,name);
	}
	
	
}
