package order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public class OrderRowMapper implements RowMapper<Order>{

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {

		return null;
	}

}
