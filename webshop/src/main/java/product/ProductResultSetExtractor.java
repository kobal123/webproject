package product;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ProductResultSetExtractor implements ResultSetExtractor<Product>{

	@Override
	public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	public Product extractData(ResultSet rs,int i) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
}
