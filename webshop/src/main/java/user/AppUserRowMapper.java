package user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.RowMapper;

public class AppUserRowMapper implements RowMapper<AppUser>{

	@Override
	public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		int dateLength = rs.getString("createdAt").length() -3;
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		List<Role> roles = new ArrayList<>();
		
		
		return new AppUser(rs.getLong("id"),
				rs.getString("name"),
				rs.getString("email"),
				rs.getString("password"),
				LocalDateTime.parse(rs.getString("createdAt").replace(' ', 'T').substring(0, dateLength)));
	
	}
}
