package product;

import java.time.LocalDateTime;


/*
 * 
 * 
 * TODO:   a double rossz ötlet pénz tárolására------ kicserélni Java currency API-ra
 * 
 * 
 * */



public record Product(
					  Long id,
					  Long userId,
					  String description,
					  String name,
					  double price,
					  LocalDateTime createdAt) {}
