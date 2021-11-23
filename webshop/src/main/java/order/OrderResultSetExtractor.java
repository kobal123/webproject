package order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class OrderResultSetExtractor implements	ResultSetExtractor<Order> {

	@Override
	public Order extractData(ResultSet rs) throws SQLException, DataAccessException {
		Order order = null;
		while(rs.next()) {
			Long id = rs.getLong("id");
			Long userId = rs.getLong("user_idd");
			Double total = rs.getDouble("grand_total");
			String shipping = rs.getString("shipping_address");
			String createdAt = rs.getString("created_at");
			String updatedAt = rs.getString("updated_at");
			LocalDateTime created = LocalDateTime
					.parse(createdAt.replace(' ', 'T').substring(0, createdAt.length()-3));
			
			LocalDateTime updated = LocalDateTime
					.parse(updatedAt.replace(' ', 'T').substring(0, updatedAt.length()-3));
			Boolean cancelled = rs.getBoolean("is_cancelled");
			order = new Order(id,userId,total,shipping,created,updated);
		}
		
		
		return order;
	}

}
