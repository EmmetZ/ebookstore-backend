package com.sjtu.se2321.backend.dto;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.sjtu.se2321.backend.entity.Order;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String address;
    private String tel;
    private String receiver;
    private OffsetDateTime createdAt;
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.address = order.getAddress();
        this.createdAt = order.getCreatedAt();
        this.receiver = order.getReceiver();
        this.tel = order.getTel();
    }
}
