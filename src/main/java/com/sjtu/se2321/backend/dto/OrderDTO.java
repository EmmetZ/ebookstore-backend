package com.sjtu.se2321.backend.dto;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.sjtu.se2321.backend.entity.Address;

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

    public void setAddress(Address address) {
        this.address = address.getAddress();
        this.receiver = address.getReceiver();
        this.tel = address.getTel();
    }

    public void addItem(OrderItemDTO item) {
        this.items.add(item);
    }
}
