package product.comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import product.Product;

@Repository
public class ProductCommentRepository implements ProductCommentDao{

	
	private final JdbcTemplate jdbcTemplate;

	
	
	public ProductCommentRepository(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}



	@Override
	public List<ProductComment> getCommentsByProductId(Long productId) {
		var sql = """
				select * from
				product_comment
				where product_id = ?
				""";
		
		return jdbcTemplate.query(sql, new RowMapper<ProductComment>() {

			@Override
			public ProductComment mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new ProductComment(rs.getLong("user_id"), rs.getLong("product_id"),rs.getString("review"), LocalDate.parse(rs.getString("created_at")));
			}
		},productId);
	}



	public void addCommentToProduct(ProductComment comment) {

		var sql = """
				insert into 
				product_comment(user_id,product_id,review,created_at)
				values(?,?,?,?)
				
				""";
		
		jdbcTemplate.update(sql,comment.getUserId(),comment.getProductId(),comment.getReview(),comment.getCreatedAt());
	}
	
	
}
