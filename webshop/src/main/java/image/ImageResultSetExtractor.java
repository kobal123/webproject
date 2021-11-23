package image;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ImageResultSetExtractor implements ResultSetExtractor<List<ProductImage>> {

	@Override
	public List<ProductImage> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<ProductImage> images = new ArrayList<>();
		
		byte[] img = null;
		while(rs.next()) {
			img = rs.getBytes("image");
			Long productId = rs.getLong("product_id");
			String path = rs.getString("path");
			images.add(new ProductImage(productId,path,img));
		}
		
		return images;
	}

}
