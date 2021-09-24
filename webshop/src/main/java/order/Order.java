package order;

import java.time.LocalDateTime;
import java.util.List;

import product.Product;

public record Order (
						Long id,
						List<Product> products,
						LocalDateTime createdAt){

	
}
