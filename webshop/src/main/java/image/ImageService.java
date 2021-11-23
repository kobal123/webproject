	package image;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class ImageService {
	private final ImageDataAccessService service;

	public ImageService(ImageDataAccessService service) {
		super();
		this.service = service;
	}
	
	
	
	/**
	 * 
	 * 
	 * @param id
	 * @return A list of products
	 */
	public List<ProductImage> getImageByProductId(Long id) {
		return service.getProductImageById(id);
	}
	
	
	public List<ProductImage> getImageByImageNameAndProductId(Long id,String name) {
		return service.getSingleProductImageByNameAndProductId(id,name);
	}
	
	
	
	
	public int saveImage(ProductImage image) {
		return service.saveImage(image);
	}

}
