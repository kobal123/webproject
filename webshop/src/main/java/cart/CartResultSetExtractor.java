package cart;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class CartResultSetExtractor implements ResultSetExtractor<Cart> {

	@Override
	public Cart extractData(ResultSet rs) throws SQLException, DataAccessException {
		while(rs.next())
			return new Cart(rs.getLong("id"), rs.getLong("user_id"),null, null);
		
		return null;
	}

}
