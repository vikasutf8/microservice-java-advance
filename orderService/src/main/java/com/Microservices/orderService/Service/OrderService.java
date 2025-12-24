package com.Microservices.orderService.Service;


import com.Microservices.orderService.Dto.InventoryResponseDto;
import com.Microservices.orderService.Dto.OrderLineItemDto;
import com.Microservices.orderService.Dto.OrderRequestDto;
import com.Microservices.orderService.Enitity.OrderEnitity;
import com.Microservices.orderService.Enitity.OrderLineItems;
import com.Microservices.orderService.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient; // restart intellij

    public void placeOrder(OrderRequestDto orderRequestDto) {
        OrderEnitity orderEnitity = new OrderEnitity();
        orderEnitity.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderRequestDto.getOrderLineItemDtos().stream()
                .map(this::maptoDto)
                .toList();
        orderEnitity.setOrderLineItemsList(orderLineItemsList);

        List<String> skuCode = orderEnitity.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

//        Before placed order first check on inventory that presnet in stock
//         calling sync way of inventory service with webclient
        // bydefualt webclient is async  call ..make it sync
        InventoryResponseDto[] inventoryResponseDtos = webClient.get()
                .uri("http://localhost:7173/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCode).build())
                .retrieve()
                .bodyToMono(InventoryResponseDto[].class).block();
// Perfromance issue :-  100 product -- 100 call as sku call ??

        assert inventoryResponseDtos != null;
        boolean allProductInStock = Arrays.stream(inventoryResponseDtos).allMatch(InventoryResponseDto::isInStock);


        if (allProductInStock) {
//        save to db
            orderRepository.save(orderEnitity);
        } else {
            throw new IllegalArgumentException("Product Out of Stock");
        }

    }

    private OrderLineItems maptoDto(OrderLineItemDto orderLineItemDto) {
        return OrderLineItems.builder()
                .price(orderLineItemDto.getPrice())
                .quantity(orderLineItemDto.getQuantity())
                .skuCode(orderLineItemDto.getSkuCode())
                .build();
    }
}
