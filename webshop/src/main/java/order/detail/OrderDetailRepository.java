package order.detail;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDetailRepository implements OrderDetailDao{

	
	private final JdbcTemplate jdbcTemplate;
	
	
	public OrderDetailRepository(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public OrderDetail getOrderDetailsById(Long orderId) {
		var sql = """
				select * from 
				order_detail
				where order_id = ? ;
				""";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<OrderDetail>() {

			@Override
			public OrderDetail extractData(ResultSet rs) throws SQLException, DataAccessException {
				OrderDetail orderDetail = null;
				
				while(rs.next()) {
					Long id = rs.getLong("id");
					Long orderId = rs.getLong("order_id");
					String firstName = rs.getString("first_name");
					String lastName = rs.getString("last_name");
					String mobileNumber = rs.getString("mobile_number");
					String address = rs.getString("address");
					String country = rs.getString("country");
					String city= rs.getString("city");
					orderDetail = new OrderDetail(id,orderId,firstName,lastName,mobileNumber,address,country,city);
				}
				
				
				return orderDetail;
			}
		},orderId);

	
	
	
}


	@Override
	public void saveOrderDetail(Long orderId, OrderDetail detail) {
		var sql = """
				insert into 
				order_detail(order_id,first_name,last_name,mobile_number,address,country,city)
				values(?,?,?,?,?,?,?);
				
	
	
				"""	;
		
		jdbcTemplate.update(sql,orderId,detail.getFirstName(),detail.getLastName(),detail.getMobileNumber(),detail.getAddress(),detail.getCountry(),detail.getCity());
				
	
	}
	
}















