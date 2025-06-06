package com.sjtu.se2321.backend.dao;

import com.sjtu.se2321.backend.entity.Address;

public interface AddressDAO {
    public Address findById(Long id);

    public void save(Address addr);

    public void deleteById(Long id);
}
