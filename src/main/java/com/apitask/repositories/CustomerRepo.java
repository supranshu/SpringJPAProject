package com.apitask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apitask.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

	Customer findByFirstName(String firstName);
}
