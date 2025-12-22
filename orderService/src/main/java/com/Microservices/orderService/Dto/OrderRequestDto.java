package com.Microservices.orderService.Dto;


import com.Microservices.orderService.Enitity.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderRequestDto {

    private List<OrderLineItemDto> orderLineItemDtos;
}
