package com.sjtu.se2321.backend.dto;

import com.sjtu.se2321.backend.entity.Address;

import lombok.Data;

@Data
public class AddressDTO {
    private Long id;
    private String tel;
    private String address;
    private String receiver;

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.tel = address.getTel();
        this.address = address.getAddress();
        this.receiver = address.getReceiver();
    }
}
