package user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;


@Repository
public class RoleDataAccessService implements RoleDao{

	private final JdbcTemplate jdbcTemplate;
	
	public RoleDataAccessService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate= jdbcTemplate;
	}
	
	@Override
	public List<Role> selectUserRolesByUsername(String username) {
		var sql = """
				SELECT * FROM role
				WHERE username = ? 
				""";
		
		List<Role> roles = jdbcTemplate.query(sql,new RoleResultSetExtractor(),username);
		
		return roles;
	}
	
	

}
