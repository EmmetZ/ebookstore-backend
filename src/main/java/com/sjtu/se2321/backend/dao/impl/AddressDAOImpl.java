package com.sjtu.se2321.backend.dao.impl;

import java.util.List;

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
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> findAllByUserId(Long userId) {
        return addressRepository.findAllByUserId(userId);
    }

}
