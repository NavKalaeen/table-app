package com.example.warehouse.management.practice.Products;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.example.warehouse.management.practice.jpa.ProductRepository;

@RestController
public class ProductJpaResource {
	
	private ProductDoaService service;
	private ProductRepository repository;
	
	
	
	public ProductJpaResource(ProductDoaService service, ProductRepository repository) {
		this.service = service;
		this.repository = repository;
		
	}
	

	
	
	@GetMapping("/jpa/products/get")
	public List<Product> retriveAllProducts(){
		List<Product> list = repository.findAll();
		if(list.isEmpty()) {
			throw new BadRequestException();
		}
		
		return list;
		
	}
	
	@GetMapping("/jpa/products/vendor")
	public List<Product> retriveOneProduct(@RequestParam("vendor") String vendor) throws Exception{
		 List<Product> pr = new ArrayList<>();
		 List<Product> prod1 = repository.findAll();
		for(Product p : prod1) {
			if(p.getVendor().contains(vendor)) {
				pr.add(p);
				break;
			}
		}
		
		if(pr.isEmpty()) {
			throw new BadRequestException();
		} else {
			return pr;
			
			
		}
	
		
		//return pr;
		 
		 //Predicate<? super Product> predicate = product -> product.getVendor().equals(vendor);
			//return prod1.stream().filter(predicate).findFirst().orElse(null);

		
	}
	
	
	@GetMapping("/jpa/products/{id}")
	public Optional<Product> retriveOneProduct(@PathVariable int id) throws Exception{
		Optional<Product> product = repository.findById(id);
		if(product.isEmpty()) {
			throw new BadRequestException();
		} else{
			return product;
		}
	}
	
	@PostMapping("/jpa/products")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		   repository.save(product);
		return ResponseEntity.created(null).build();
	}
	
	@DeleteMapping("/jpa/products/{id}")
	public void deleteOneProduct(@PathVariable int id) throws Exception{
		Optional<Product> val = repository.findById(id);	
		if(val.isEmpty()) {
			throw new BadRequestException();
		}
		repository.deleteById(id);
	}
	
	@PutMapping("/jpa/products/{id}")
	public void updateProduct(@RequestBody Product product, @PathVariable int id) {
		Optional<Product> val = repository.findById(id);
		if(val.isEmpty()) {
			throw new BadRequestException();
		} else {
			List<Product> prod = repository.findAll();
			for(Product p : prod) {
				if(p.getId() == id) {
					p.setPrice(product.getPrice());
					p.setStock(product.getStock());
					repository.save(p);
					break;
				}
			}
			
		}
		
		
	}

}
