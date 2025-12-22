package com.Microservices.inventoryService.Repoository;

import com.Microservices.inventoryService.Enitity.InventoryEnitity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<InventoryEnitity, Long> {
    Optional<InventoryEnitity> findBuSkuCode();
}
