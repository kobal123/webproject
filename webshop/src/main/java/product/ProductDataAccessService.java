package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
				order by id desc
				LIMIT 20;
				""";
		
		return jdbcTemplate.query(sql, new ProductRowMapper());
	}
	
	
	
	

	@Override
	public Long addProduct(Product p) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		
		var sql = """ 
				insert into product(user_id,price,quantity,created_At,updated_At,product_name,description)
				values(?,?,?,?,?,?,?) ;
				""";
		
		
		
		jdbcTemplate.update(
				  new PreparedStatementCreator() {
				    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				      PreparedStatement statement = connection.prepareStatement(sql,new String[] { "id" });
				      statement.setLong(1, p.getUserId());
				      statement.setDouble(2, p.getPrice());
				      statement.setInt(3, p.getQuantity());
				      statement.setObject(4, p.getCreatedAt());
				      statement.setObject(5, p.getDeletedAt());
				      statement.setString(6,p.getName());
				      statement.setString(7, p.getDescription());
				      return statement;
				    }
				  }, keyHolder);
	return keyHolder.getKey().longValue();
	}

	@Override
	public List<Product> selectProductsByUserId(Long id) {

		var sql = """ 
				SELECT * 
				FROM product
				where product.user_id = ? ;
				""";
		
		
		return jdbcTemplate.query(sql,new ProductRowMapper(),id);
	}

	@Override
	public Product getProductById(Long id) {
		var sql = """ 
				SELECT * 
				FROM product
				where product.id = ? ;
				""";
		return jdbcTemplate.query(sql,new ProductResultSetExtractor(),id);
	}

	@Override
	public Product getProductByIdAndName(Long id, String name) {
		var sql = """ 
				SELECT * 
				FROM product
				where product.id = ? and product.product_name = ? ;
				""";
		
		return jdbcTemplate.query(sql, new ProductResultSetExtractor(),id,name);
	}

	@Override
	public List<String> getProductNamesLikeInput(String name) {
		var sql = """ 
				SELECT DISTINCT product_name
				FROM product
				where product_name like ?
				LIMIT 10;
				""";
		
		return jdbcTemplate.query(sql,new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				
				return new String(rs.getString("product_name"));
			}
		
		}, "%"+name+"%");
	}

	@Override
	public List<Product> getProductsByName(String name) {
		var sql = """ 
				SELECT *
				FROM product
				where product_name like ?
				order by id desc
				LIMIT 100;
				""";
		

		return jdbcTemplate.query(sql, new ProductRowMapper(),"%"+name+"%");
	
	}

}









