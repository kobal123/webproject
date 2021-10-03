package product;

import java.time.LocalDateTime;


/*
 * 
 * 
 * TODO:   a double rossz ötlet pénz tárolására------ kicserélni Java currency API-ra
 * 
 * 
 * */



public class Product{
	private Long id;
	private Long userId;
	private Double price;
	private Integer Quantity;
	private Integer Discount;
	private LocalDateTime  createdAt;
	private LocalDateTime  deletedAt;
	
}
