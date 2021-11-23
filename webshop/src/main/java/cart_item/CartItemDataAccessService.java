package cart_item;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CartItemDataAccessService implements CartItemDao{
	private JdbcTemplate jdbcTemplate;
	
	public CartItemDataAccessService(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	
	
	@Override
	public List<CartItem> getCartItemsByCartId(Long id) {
		var sql = """
				select * from
				cart_item
				where cart_id = ?
				""";
		
		return jdbcTemplate.query(sql, new CartItemRowMapper(),id);
	}

	@Override
	public CartItem getSingleCartItemById(Long id) {
		var sql = """
				select * from
				cart_item
				where id = ?
				""";
		return jdbcTemplate.query(sql,new CartItemResultSetExtractor(), id);
	}


	
	/**
	 * Adds a shopping cart item to a user's shopping cart
	 * 
	 * @param item
	 * 	The item to be added to the user's shopping cart
	 * @return
	 */
	public Long addCartItemToCart(CartItem item) {
		var sql = """
				insert into cart_item(cart_id,product_id,quantity,total,created_at)
				values(?,?,?,?,?)

				""";
		
		Long cartId = item.getCartId();
		Long productId = item.getProductId();
		Integer quantity = item.getQuantity();
		Double total = item.getPrice();
		LocalDateTime createdAt = LocalDateTime.now();
		
		jdbcTemplate.update(sql,cartId,productId,quantity,total,createdAt);
		
		
		
		return null;
	}



	@Override
	public void updateCartItem(Long id, int quantity) {
		var sql = """
				update cart_item
				set quantity = ?
				where product_id = ?;

				""";
		
		jdbcTemplate.update(sql,quantity,id);
		
		
		
	}



	@Override
	public void deleteCartItemsByCartId(Long cartId) {
		var sql = """
				delete from cart_item
				where cart_id =?
				""";
		
		jdbcTemplate.update(sql, cartId);
	}



	@Override
	public void deleteCartItemById(Long cartItemId) {
		var sql = """
				delete from cart_item
				where id = ?
				""";
		
		jdbcTemplate.update(sql, cartItemId);		
	}

}
