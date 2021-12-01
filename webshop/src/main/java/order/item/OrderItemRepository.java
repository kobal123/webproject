package order.item;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemRepository implements OrderItemDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
		var sql = """
				select * from
				order_item
				where order_id = ?
				""";
		
		return jdbcTemplate.query(sql, new OrderItemRowMapper(),orderId);
	}

	@Override
	public OrderItem getOrderItemById(Long orderItemId) {
		
		
		return null;
	}

	@Override
	public int[] saveMultipleOrderItems(List<OrderItem> items) {
		var sql = """
				insert into 
				order_item
				(order_id,product_id,total,quantity)
				values(?,?,?,?)
				""";
		
		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {

				OrderItem item = items.get(i);
				ps.setLong(1, item.getOrderId());
				ps.setLong(2, item.getProductId());
				ps.setDouble(3, item.getGrandTotal());
				ps.setInt(4, item.getQuantity());
				
				
			}
			
			@Override
			public int getBatchSize() {
				return items.size();
			}
		});


	}

}
