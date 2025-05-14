package com.sjtu.se2321.backend.dao;

import java.util.List;

import com.sjtu.se2321.backend.entity.Address;

public interface AddressDAO {
    public Address findById(Long id);

    public List<Address> findAllByUserId(Long userId);
}
