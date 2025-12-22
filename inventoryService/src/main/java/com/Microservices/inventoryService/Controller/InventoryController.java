package com.Microservices.inventoryService.Controller;

import com.Microservices.inventoryService.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProductIventory(){

    }

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public  boolean isInStock(@PathVariable("sku-code") String skucode){
        return inventoryService.isInStock(skucode);
    }
}
