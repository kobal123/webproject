package user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class RoleResultSetExtractor implements ResultSetExtractor<List<Role>>{

	@Override
	public List<Role> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Role> roles = new ArrayList<>();

		while(rs.next()) {
			Role r = new Role(rs.getString("username"), rs.getString("rolename"));
			roles.add(r);
		}
		
		return roles;
	}

	


}
