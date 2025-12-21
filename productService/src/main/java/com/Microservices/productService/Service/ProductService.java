package com.Microservices.productService.Service;


import com.Microservices.productService.Dto.ProductRequestDto;
import com.Microservices.productService.Dto.ProductResponseDto;
import com.Microservices.productService.Entity.ProductEnitity;
import com.Microservices.productService.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

//    constructor injection

    private final ProductRepository productRepository;

    public void  createProduct(ProductRequestDto productRequestDto){

        ProductEnitity product  = ProductEnitity.builder()
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product  {} created Succefully", product.getId() );
    }

    public List<ProductResponseDto>  getAllProduct(){
        List<ProductEnitity> productEnitities =productRepository.findAll();

//         list of ProductEnitity convert into  List RequestResposneDtpo

        List<ProductResponseDto> productResponseDtos =productEnitities.stream().map(product-> productToProductResponse(product)).toList();
            log.info("Fetch all products");
        return productResponseDtos;
    }

    private ProductResponseDto productToProductResponse(ProductEnitity product) {
        return ProductResponseDto
                .builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
