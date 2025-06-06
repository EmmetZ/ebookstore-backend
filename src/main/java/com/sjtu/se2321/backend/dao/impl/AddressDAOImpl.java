package com.sjtu.se2321.backend.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sjtu.se2321.backend.dao.AddressDAO;
import com.sjtu.se2321.backend.entity.Address;
import com.sjtu.se2321.backend.repository.AddressRepository;

@Component
public class AddressDAOImpl implements AddressDAO {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Address addr) {
        addressRepository.save(addr);
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
