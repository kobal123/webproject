package product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ProductResultSetExtractor implements ResultSetExtractor<Product>{

	@Override
	public Product extractData(ResultSet rs) throws SQLException, DataAccessException {

		
		while(rs.next()) {
		Long id = rs.getLong("id");
		Long userID = rs.getLong("user_id");
		Double price = rs.getDouble("price");
		Integer quantity = rs.getInt("quantity");
		String name = rs.getString("product_name");
		String createdAt = rs.getString("created_at");
		String updatedAt = rs.getString("updated_at");
		return new Product(id,userID,name,price,quantity,LocalDateTime
				.parse(createdAt.replace(' ', 'T').substring(0, createdAt.length()-3)),
				LocalDateTime.now());
		}

		return null;
	}

	

}
