package com.Microservices.inventoryService;

import com.Microservices.inventoryService.Enitity.InventoryEnitity;
import com.Microservices.inventoryService.Repoository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}


    @Bean
    public CommandLineRunner localData(InventoryRepository inventoryRepository){
        return  args -> {
            InventoryEnitity inventoryEnitity =new InventoryEnitity();
            inventoryEnitity.setSkuCode("iphone-15");
            inventoryEnitity.setQuantity(100);

            InventoryEnitity inventoryEnitity1 =new InventoryEnitity();
            inventoryEnitity1.setSkuCode("iphone-15-red");
            inventoryEnitity1.setQuantity(0);

            inventoryRepository.save(inventoryEnitity1);
            inventoryRepository.save(inventoryEnitity);

        };
    }
}
