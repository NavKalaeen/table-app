package com.example.warehouse.management.practice.Products;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.*;
import org.springframework.stereotype.Component;

@Component
public class ProductDoaService {

	private static List<Product> products = new ArrayList<>();
	private static int idCount = 0;
	
	
	
static {
	products.add(new Product(++idCount,"Test","test","test",1,12,"123","ddn","cvf"));
	}
	
	

	
	public List<Product> findAll() {
		return products;
	}
	
	public Product findOne(int id) {
		Predicate<? super Product> predicate = product -> (product.getId() == id);
		//return products.stream().filter(predicate).findFirst().orElse(null);
		return products.stream().filter(predicate).findFirst().orElse(null);
		
	}
	
	public Product findVendor(String vendor) {
		Predicate<? super Product> predicate = product -> product.getVendor().equals(vendor);
		return products.stream().filter(predicate).findFirst().orElse(null);
		
	}
	
	public void deleteOne(int id) {
		Predicate<? super Product> predicate = product -> product.getId() == id;
		products.removeIf(predicate);
	}
	
	public Product save(Product product){
		product.setId(++idCount);
		products.add(product);
		return product;
	}
	
	
}
