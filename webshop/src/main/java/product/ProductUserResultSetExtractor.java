package product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ProductUserResultSetExtractor implements ResultSetExtractor<List<Product>> {

	@Override
	public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException {

		List<Product> products = new ArrayList<>();
		while(rs.next()) {
			Long id = rs.getLong("id");
			Long userId = rs.getLong("user_id");
			Double price = rs.getDouble("price");
			Integer quant = rs.getInt("quantity");
			LocalDateTime createdAt = LocalDateTime.parse(rs.getString("createdAt"));
			LocalDateTime updatedAt = LocalDateTime.parse(rs.getString("updateddAt"));
			String name = rs.getString("product_name");
			products.add(new Product(id,userId,name,price,quant,createdAt,updatedAt));
			
		}
		
		
		return products;
	}

}
