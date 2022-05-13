package admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class MySessionRepository  implements SessionDao{

	private final JdbcTemplate jdbcTemplate;
	
	
	
	
	public MySessionRepository(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}




	public Map<String, Object> sessions() {
		var sql = """
				SELECT primary_id,  principal_name FROM spring_session
				where principal_name is not null;
				""";
		
		return  jdbcTemplate.query(sql, new ResultSetExtractor<Map<String,Object>>() {

			@Override
			public Map<String, Object> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String,Object> map = new HashMap<>();
				while(rs.next()) {
					map.put(rs.getString("primary_id"), (String)rs.getObject("principal_name"));
				}
				
				return map;
			}
		});
	}




	@Override
	public void deleteSessionById(String sessionId) {
		
		var sql = """
				delete from spring_session
				where primary_id = ?
				""";
		
		
		jdbcTemplate.update(sql,sessionId);
		
		
		
	}

}
