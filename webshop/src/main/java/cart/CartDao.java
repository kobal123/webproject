package cart;




public interface CartDao {

	public Cart getCartByUserId(Long id);
	
	
	public void createCartForNewUserById(Long userId); 
}
