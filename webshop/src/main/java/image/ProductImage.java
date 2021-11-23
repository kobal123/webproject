package image;

import java.util.Arrays;
import java.util.UUID;

public class ProductImage {

	private  Long product_id;
	private byte[] data;
	private String path;
	
	public ProductImage(Long product_id,byte[] data) {
		super();
		
		this.path=UUID.randomUUID().toString()+data.hashCode();
		this.product_id = product_id;
		this.data = data;
	}
	
	public ProductImage(Long product_id, String path,byte[] data) {
		super();
		
		this.path=path;
		this.product_id = product_id;
		this.data = data;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "ProductImage [product_id=" + product_id + ", data=" + Arrays.toString(data) + ", path=" + path + "]";
	}
	

	
}
