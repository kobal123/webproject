package order.detail;

public class OrderDetail {

	private Long id;
	private Long orderId;
	

	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String address;
	private String country;
	private String city;
	
	public OrderDetail(Long id, Long orderId, String firstName, String lastName, String mobileNumber, String address,
			String country, String city) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.country = country;
		this.city = city;
	}


	public OrderDetail(String firstName, String lastName, String mobileNumber, String address, String country,
			String city) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.country = country;
		this.city = city;
	}
	
	
	public OrderDetail() {
		super();
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

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderDetail [id=");
		builder.append(id);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", mobileNumber=");
		builder.append(mobileNumber);
		builder.append(", address=");
		builder.append(address);
		builder.append(", country=");
		builder.append(country);
		builder.append(", city=");
		builder.append(city);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
}
