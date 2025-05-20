package com.sjtu.se2321.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjtu.se2321.backend.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

    public List<Address> findAllByUserId(Long userId);

}