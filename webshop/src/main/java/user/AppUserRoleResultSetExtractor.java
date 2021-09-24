package user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class AppUserRoleResultSetExtractor implements ResultSetExtractor<AppUser>{

	@Override
	public AppUser extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Long,AppUser> users = new HashMap<>();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Long temp_id=0l;
		
		
		while(rs.next()) {
			String date = rs.getString("createdAt");
			int dateLength = date.length() -3;
			
			Long id  = rs.getLong("id");
			if(temp_id ==0l)
				temp_id=id;
			String name = rs.getString("name");
			String email = rs.getString("email");
			String password = rs.getString("password");

			String rolename = rs.getString("rolename");
			
			AppUser user = users.get(id);
			
			if(user == null) {
				user = new AppUser(id,name,email,password,
						LocalDateTime.parse(date.replace(' ', 'T').substring(0, dateLength)));
				users.put(id, user);
			}
			user.addRoles( new Role(name,rolename));
			
		}
		System.out.println(users.get(temp_id).toString());
		return users.get(temp_id);
	}

}
