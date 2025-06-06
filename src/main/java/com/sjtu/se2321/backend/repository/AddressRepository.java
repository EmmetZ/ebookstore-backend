package com.sjtu.se2321.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjtu.se2321.backend.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}