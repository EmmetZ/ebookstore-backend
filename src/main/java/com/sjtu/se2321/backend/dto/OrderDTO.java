package com.sjtu.se2321.backend.dto;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.sjtu.se2321.backend.entity.Order;

import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private String receiver;
    private String address;
    private String tel;
    private OffsetDateTime createdAt;
    private List<OrderItemDTO> items;

    public OrderDTO() {
        this.items = new ArrayList<>();
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.receiver = order.getReceiver();
        this.address = order.getAddress();
        this.tel = order.getTel();
        this.createdAt = order.getCreatedAt();
        this.items = new ArrayList<>();
    }

    public void addItem(OrderItemDTO item) {
        this.items.add(item);
    }
}
