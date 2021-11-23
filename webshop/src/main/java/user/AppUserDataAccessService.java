package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class AppUserDataAccessService implements UserDao {

	private final JdbcTemplate jdbcTemplate;
	
	public AppUserDataAccessService(JdbcTemplate jdbctemplate) {
		this.jdbcTemplate = jdbctemplate;
	}
	
	
	@Override
	public List<AppUser> selectUsers() {
		var sql = """ 
				SELECT *
				FROM users
				LIMIT 10;
				""";
		
		return jdbcTemplate.query(sql,new AppUserRowMapper() );
	}
	
	


	@Override
	public Long addUsers(AppUser user) {
		var sql ="""
				INSERT INTO
				 users(name,email,password,createdAt)
				 VALUES(?,?,?,?)
				""";

		
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(
				  new PreparedStatementCreator() {
				    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				      PreparedStatement statement = connection.prepareStatement(sql,new String[] { "id" });
				      statement.setString(1, user.getName());
				      statement.setString(2,user.getEmail());
				      statement.setString(3, user.getPassword());
				      statement.setObject(4,user.getCreatedAt());
				      return statement;
				    }
				  }, keyHolder);
	return keyHolder.getKey().longValue();
		
		
	}

	
	@Override
	public AppUser selectUserByUserName(String username) {
		String sql = """
				SELECT * FROM users INNER JOIN role on users.name = role.username
				WHERE name = ? ;
				 """;
	
		//return jdbcTemplate.queryForObject(sql,new AppUserRowMapper(), username);
		return jdbcTemplate.query(sql, new AppUserRoleResultSetExtractor(), username);
		
	}


	@Override
	public AppUser selectUserByUserId(Long id) {
		var sql = """
				SELECT * FROM users 
				WHERE users.id = ? ;
				 """;
					
		
		
		return jdbcTemplate.queryForObject(sql,new AppUserRowMapper(),id );
	}
	


}
