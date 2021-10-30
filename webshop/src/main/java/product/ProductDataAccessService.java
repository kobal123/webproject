package product;

import java.time.LocalDateTime;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;

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
				FROM product
				LIMIT 20;
				""";
		
		return jdbcTemplate.query(sql, new ProductRowMapper());
	}
	
	
	
	

	@Override
	public int addProduct(Product p) {
		var sql = """ 
				insert into product(user_id,price,quantity,created_At,updated_At,product_name)
				values(?,?,?,?,?,?) ;
				""";
		
		return jdbcTemplate.update(sql,p.getUserId(),p.getPrice(),p.getQuantity(),p.getCreatedAt(),null,p.getName());
	}

	@Override
	public List<Product> selectProductsByUserId(Long id) {

		var sql = """ 
				SELECT * 
				FROM product
				where product.user_id = ? ;
				""";
		
		
		return jdbcTemplate.query(sql,new ProductRowMapper());
	}

}
