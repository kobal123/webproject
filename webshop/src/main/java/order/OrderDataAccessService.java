package order;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDataAccessService implements OrderDao{
	private final JdbcTemplate jdbcTemplate;
	
	public OrderDataAccessService(JdbcTemplate template) {
		this.jdbcTemplate = template;
	}

	@Override
	public List<Order> getOrdersByUserId(Long id) {
		
		var sql= """
				select * from
				orders where orders.user_id = ?
				""";
		
		
		
		return jdbcTemplate.query(sql, new OrderResultSetExtractor(),id);
	}

	@Override
	public Order getOrderByOrderId() {
		return null;
	}

	@Override
	public void saveOrder() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
}
