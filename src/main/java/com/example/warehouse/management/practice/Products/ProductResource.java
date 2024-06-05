package com.example.warehouse.management.practice.Products;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.warehouse.management.practice.Products.Exceptions.BadRequestException;

@RestController
public class ProductResource {
	
	private ProductDoaService service;
	
	public ProductResource(ProductDoaService service) {
		this.service = service;
		
	}

	
	
	@GetMapping("/products/get")
	public List<Product> retriveAllProducts(){
		List<Product> list = service.findAll();
		if(list.isEmpty()) {
			throw new BadRequestException();
		}
		
		return list;
		
	}
	
	@GetMapping("/products/vendor")
	public Product retriveOneProduct(@RequestParam("vendor") String vendor) throws Exception{
		Product product = service.findVendor(vendor);	
		if(product == null) {
			throw new BadRequestException();
		}
		return product;
	}
	
	@GetMapping("/products/{id}")
	public Product retriveOneProduct(@PathVariable int id) throws Exception{
		Product product = service.findOne(id);	
		if(product == null) {
			throw new BadRequestException();
		}
		return product;
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		service.save(product);
		
		return ResponseEntity.created(null).build();
	}
	
	@DeleteMapping("/products/{id}")
	public void deleteOneProduct(@PathVariable int id) throws Exception{
		Product product = service.findOne(id);	
		if(product == null) {
			throw new BadRequestException();
		}
		service.deleteOne(id);
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable int id) {
		Product product1 = service.findOne(id);
		if(product1 == null) {
			throw new BadRequestException();
		}
		
		product1.setPrice(product.getPrice());
		product1.setStock(product.getStock());
		
		return ResponseEntity.ok(product1);
		
	}

}
