package order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public class OrderRowMapper implements RowMapper<List<Order>>{

	@Override
	public List<Order> mapRow(ResultSet rs, int rowNum) throws SQLException {

		return null;
	}

}
