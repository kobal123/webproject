package image;

import org.springframework.stereotype.Service;

@Service
public class ImageService {
	private final ImageDataAccessService service;

	public ImageService(ImageDataAccessService service) {
		super();
		this.service = service;
	}
	
	
	public byte[] getImageByProductId(Long id) {
		return service.getProductImageById(id);
	}
	
	

}
