package com.Microservices.orderService.Service;


import com.Microservices.orderService.Dto.OrderLineItemDto;
import com.Microservices.orderService.Dto.OrderRequestDto;
import com.Microservices.orderService.Enitity.OrderEnitity;
import com.Microservices.orderService.Enitity.OrderLineItems;
import com.Microservices.orderService.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private  final OrderRepository orderRepository;

    public void placeOrder(OrderRequestDto orderRequestDto){
        OrderEnitity orderEnitity =new OrderEnitity();
        orderEnitity.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList =orderRequestDto.getOrderLineItemDtos().stream()
                .map(this::maptoDto)
                .toList();
        orderEnitity.setOrderLineItemsList(orderLineItemsList);

//        save to db
orderRepository.save(orderEnitity);

    }

    private OrderLineItems maptoDto(OrderLineItemDto orderLineItemDto) {
        return  OrderLineItems.builder()
                .price(orderLineItemDto.getPrice())
                .quantity(orderLineItemDto.getQuantity())
                .skuCode(orderLineItemDto.getSkuCode())
                .build();
    }
}
