package order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDataAccessService implements OrderDao{
	private final JdbcTemplate jdbcTemplate;
	
	public OrderDataAccessService(JdbcTemplate template) {
		this.jdbcTemplate = template;
	}

	@Override
	public List<Order> getOrdersByUserId(Long userId) {
		
		var sql= """
				select * from
				orders where orders.user_id = ?
				""";
		
		
		
		return jdbcTemplate.query(sql, new OrderRowMapper(),userId);
	}

	@Override
	public Order getOrderByOrderId(Long orderId) {
		
		var sql= """
				select * from
				orders where orders.id = ?
				""";
		
		
		
		return jdbcTemplate.query(sql, new OrderResultSetExtractor(),orderId);
	}

	
	
	@Override
	public Long saveOrder(Order order) {
		var sql ="""
				INSERT INTO
				 orders(user_id,grand_total,shipping_address,created_At,updated_At)
				 values(?,?,?,?,?)
				""";

		
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(
				  new PreparedStatementCreator() {
				    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				      PreparedStatement statement = connection.prepareStatement(sql,new String[] { "id" });
				      statement.setLong(1, order.getUserId());
				      statement.setDouble(2, order.getGrandTotal());
				      statement.setString(3, order.getShippingAddress());
				      statement.setObject(4, order.getCreatedAt());
				      statement.setObject(5, order.getUpdatedAt());

				      return statement;
				    }
				  }, keyHolder);
	return keyHolder.getKey().longValue();
	}
	
	
	
	
	
	
}
