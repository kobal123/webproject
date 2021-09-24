package product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDataAccessService implements ProductDao{

	
	private final JdbcTemplate jdbcTemplate;
	
	
	public ProductDataAccessService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Product> selectProducts() {
		var sql = """ 
				SELECT * 
				FROM products
				LIMIT 20;
				""";
		
		return jdbcTemplate.query(sql, new ProductRowMapper());
	}

}
