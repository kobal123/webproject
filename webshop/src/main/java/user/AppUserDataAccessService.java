package user;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
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
	public int addUsers(AppUser user) {
		var sql ="""
				INSERT INTO
				 users(name,email,password,createdAt)
				 VALUES(?,?,?,?)
				""";

		
		System.out.println(user.toString());
		return jdbcTemplate.update(sql,user.getName(),user.getEmail(),user.getPassword(),user.getCreatedAt());
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
	


}
