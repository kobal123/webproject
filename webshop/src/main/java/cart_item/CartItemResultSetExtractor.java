package cart_item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class CartItemResultSetExtractor implements ResultSetExtractor<CartItem> {

	@Override
	public CartItem extractData(ResultSet rs) throws SQLException, DataAccessException {
		while(rs.next()) {
			
			return new CartItem(rs.getLong("id"),rs.getLong("cart_id"),rs.getLong("product_id"),
					rs.getInt("quantity"),rs.getDouble("total"),
					LocalDateTime.now(),
					LocalDateTime.now());
		}
		return null;
	}

}
