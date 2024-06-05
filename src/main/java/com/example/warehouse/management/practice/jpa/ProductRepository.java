package com.example.warehouse.management.practice.jpa;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.warehouse.management.practice.Products.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {


}

