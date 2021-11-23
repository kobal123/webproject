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
	public List<ProductImage> getProductImageById(Long id) {
		var sql=""" 
				select * from
				images
				where product_id = ? 
				
				""";
		
		
		return jdbcTemplate.query(sql, new ImageRowMapper(),id);
	}

	@Override
	public int saveImage(ProductImage image) {
		var sql=""" 
				insert into images(product_id,image,path)
				values(?,?,?);
				
				""";
		
		
		
		return jdbcTemplate.update(sql,image.getProduct_id(),image.getData(),image.getPath());
	}

	/**
	 *this returns a list with one Image, where the image path is the name given.
	 */
	@Override
	public List<ProductImage> getSingleProductImageByNameAndProductId(Long id,String name) {
		
		var sql=""" 
				select *
				from images
				where images.path =? and images.product_id=?
				""";
		
		
		return jdbcTemplate.query(sql, new ImageResultSetExtractor(),name,id);
	}
	
	
	
	
	
}
