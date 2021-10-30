package image;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDataAccessService implements ImageDao{

	private final JdbcTemplate jdbcTemplate;

	public ImageDataAccessService(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<byte[]> getProductImagesById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getProductImageById(Long id) {
		var sql=""" 
				select image from 
				product 
				 inner join
				productImages on product.id = productmages.product_id
				 inner join images on images.image_id = productimages.image_id
				
				where product.id = ? 
				
				""";
		
		
		return jdbcTemplate.query(sql, new ImageResultSetExtractor(),id);
	}
	
	
	
	
	
}
