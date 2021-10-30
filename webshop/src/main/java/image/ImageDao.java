package image;

import java.util.List;

public interface ImageDao {

	List<byte[]> getProductImagesById(Long id);
	
	byte[] getProductImageById(Long id);

	
}
