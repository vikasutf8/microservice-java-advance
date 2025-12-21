package com.Microservices.productService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mongodb.MongoDBContainer;

@SpringBootTest
@Testcontainers
class ProductServiceApplicationTests {
    /*
    
    * Integration test
    **/
    @Container
    static  MongoDBContainer mongoDBContainer = new MongoDBContainer("mongodb.4"); //container wiht specific versions

    // statically set mongodb url and fetch it for tests
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }


	@Test
	void contextLoads() {
	}

}
 