package order.item;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(id, itemPrice, orderId, productId, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id) && Objects.equals(itemPrice, other.itemPrice)
				&& Objects.equals(orderId, other.orderId) && Objects.equals(productId, other.productId)
				&& Objects.equals(quantity, other.quantity);
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
		return itemPrice*quantity;
	}
	public Integer getQuantity() {
		return quantity;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	
	
	
	
}
