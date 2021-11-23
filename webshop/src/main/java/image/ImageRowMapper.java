package image;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ImageRowMapper implements RowMapper<ProductImage> {

	@Override
	public ProductImage mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ProductImage(rs.getLong("product_id"),rs.getString("path"),rs.getBytes("image"));
	}

}
