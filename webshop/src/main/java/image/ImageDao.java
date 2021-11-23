package image;

import java.util.List;

public interface ImageDao {

	List<byte[]> getProductImagesById(Long id);
	
	List<ProductImage> getProductImageById(Long id);
	
	List<ProductImage> getSingleProductImageByNameAndProductId(Long id,String name);
	
	int saveImage(ProductImage image);
	
	
	

	
}
