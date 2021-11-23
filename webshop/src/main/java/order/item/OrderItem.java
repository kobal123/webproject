package order.item;

public class OrderItem {

	private Long id;
	private Long productId;
	private Long orderId;
	private Double itemPrice;
	private Integer quantity;
	public OrderItem(Long productId, Long orderId, Double grandTotal, Integer quantity) {
		super();
		this.productId = productId;
		this.orderId = orderId;
		this.itemPrice = grandTotal;
		this.quantity = quantity;
	}
	
	public OrderItem(Long productId, Double grandTotal, Integer quantity) {
		super();
		this.productId = productId;
		
		this.itemPrice = grandTotal;
		this.quantity = quantity;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setGrandTotal(Double grandTotal) {
		this.itemPrice = grandTotal;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public OrderItem(Long id,Long productId, Long orderId, Double grandTotal, Integer quantity) {
		super();
		this.id = id;
		this.productId = productId;
		this.orderId = orderId;
		this.itemPrice = grandTotal;
		this.quantity = quantity;
	}
	
	public Long getId() {
		return id;
	}
	public Long getProductId() {
		return productId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public Double getGrandTotal() {
		return itemPrice;
	}
	public Integer getQuantity() {
		return quantity;
	}

	
	
	
	
}
