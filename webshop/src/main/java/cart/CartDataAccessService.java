package cart;

import java.time.LocalDateTime;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartDataAccessService implements CartDao {
	private JdbcTemplate jdbcTemplate;
	
	
	
	
	
	public CartDataAccessService(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}





	@Override
	public Cart getCartByUserId(Long id) {
		var sql =""" 
				select * from
				cart 
				where user_id = ?;
				""";
		
		return jdbcTemplate.query(sql, new CartResultSetExtractor(),id);
	}





	@Override
	public void createCartForNewUserById(Long userId) {
		var sql =""" 
				insert into cart(user_id,created_at)
				values(?,?);
				""";
		
		jdbcTemplate.update(sql, userId, LocalDateTime.now());
	}

}
