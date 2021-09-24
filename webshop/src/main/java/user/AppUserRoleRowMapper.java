package user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class AppUserRoleRowMapper implements RowMapper<AppUser>{

	@Override
	public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {

		
		System.out.println();
		Map<Long,AppUser> users = new HashMap<>();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		int dateLength = rs.getString("createdAt").length() -3;
		
		while(rs.next()) {
			Long id  = rs.getLong("id");
			String name = rs.getString("name");
			String email = rs.getString("email");
			String password = rs.getString("password");
			Timestamp createdAt = rs.getTimestamp("createdAt");
			String rolename = rs.getString("rolename");
			
			AppUser user = users.get(id);
			
			if(user == null) {
				user = new AppUser(id,name,email,password,
						LocalDateTime.parse(rs.getString("createdAt").replace(' ', 'T').substring(0, dateLength)));
				users.put(id, user);
			}
			user.addRoles( new Role(name,rolename));
			
		}
			
		return users.get(rs.getLong("id"));
	}

}
