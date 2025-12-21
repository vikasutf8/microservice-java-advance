package com.Microservices.productService.Controller;


import com.Microservices.productService.Dto.ProductRequestDto;
import com.Microservices.productService.Dto.ProductResponseDto;
import com.Microservices.productService.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController  {

    private  final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  void createProduct(@RequestBody ProductRequestDto productRequestDto){
        productService.createProduct(productRequestDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDto> getAllProduct(){
       return productService.getAllProduct();
    }
}
