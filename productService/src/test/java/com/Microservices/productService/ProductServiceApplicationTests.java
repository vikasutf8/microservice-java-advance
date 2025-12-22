package com.Microservices.productService;

import com.Microservices.productService.Dto.ProductRequestDto;
import com.Microservices.productService.Dto.ProductResponseDto;
import com.Microservices.productService.Repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mongodb.MongoDBContainer;
import tools.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {


    /*
    
    * Integration test
    **/
    @Container
    static  MongoDBContainer mongoDBContainer = new MongoDBContainer("mongodb.4"); //container wiht specific versions
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductRepository productRepository;
    // statically set mongodb url and fetch it for tests
    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }


    @Test
    void createProductTest() throws Exception {

        ProductRequestDto productRequestDto = getProductRequest();
        String body = objectMapper.writeValueAsString(productRequestDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        ).andExpect(status().isCreated());
        Assertions.assertEquals(1, productRepository.findAll().size());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productRequestDto))
        ).andExpect(status().isCreated());

        // Act → call GET API
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/product")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                // Assert
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.size()").value(1))
//                .andExpect(jsonPath("$[0].name").value("Iphone 15"))
//                .andExpect(jsonPath("$[0].description").value("This is apple mobile divce"));
    }

    private ProductRequestDto getProductRequest() {
        return  ProductRequestDto.builder()
                .name("Iphone 15")
                .description("This is apple mobile divce")
                .price(345.3534)
                .build();
    }
//
//    @Test
//    List<ProductResponseDto> getAllProoduct(){
//
//    }

}
 