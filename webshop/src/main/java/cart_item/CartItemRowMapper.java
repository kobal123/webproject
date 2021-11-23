package cart_item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

public class CartItemRowMapper implements RowMapper<CartItem> {

	@Override
	public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new CartItem(rs.getLong("id"),rs.getLong("cart_id"),rs.getLong("product_id"),
				rs.getInt("quantity"),rs.getDouble("total"),
				LocalDateTime.now(),
				LocalDateTime.now());
	}

}
