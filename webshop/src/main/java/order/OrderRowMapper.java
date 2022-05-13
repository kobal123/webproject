package order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public class OrderRowMapper implements RowMapper<Order>{

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {

		Long id = rs.getLong("id");
		Long userId = rs.getLong("user_id");
		Double grand_total = rs.getDouble("grand_total");
		String shipping_address = rs.getString("shipping_address");
		//LocalDateTime createdAt = (LocalDateTime)rs.getObject("created_at");
		
		return new Order(id,userId,grand_total,shipping_address,LocalDate.parse(rs.getString("created_at")),null);
	}

}
