package com.Microservices.orderService.Repository;

import com.Microservices.orderService.Enitity.OrderEnitity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEnitity, Long> {
}
