package order.item;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrderItemRowMapper implements RowMapper<OrderItem> {

	@Override
	public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		Long orderId  = rs.getLong("order_id");
		Long productId  = rs.getLong("product_id");
		Double total = rs.getDouble("total");
		
		Integer quantity = rs.getInt("quantity");
		Double itemPrice = total/quantity;
		
		
		return new OrderItem(id, productId, orderId, total, quantity);
	}

}
