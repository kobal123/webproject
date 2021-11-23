
public class OrderItem {

	
	private Long id;
	private Long orderId;
	private Long productId;
	private Integer quantity;
	private Double total;
	
		
	
	
	/**
	 * Only should be used when inserting a new Order item into the database
	 * 
	 * @param orderId
	 * @param productId
	 * @param quantity
	 * @param total
	 */
	public OrderItem(Long orderId, Long productId, Integer quantity, Double total) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.total = total;
	}
	public OrderItem(Long id, Long orderId, Long productId, Integer quantity, Double total) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.total = total;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getTotal() {
		return total;
	}	 
	public void setTotal(Double total) {
		this.total = total;
	}
	
	
	
	
	
}
