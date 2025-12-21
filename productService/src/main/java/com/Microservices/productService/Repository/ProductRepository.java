package com.Microservices.productService.Repository;

import com.Microservices.productService.Entity.ProductEnitity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository  extends MongoRepository<ProductEnitity,String> {
}
