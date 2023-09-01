package com.apitask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apitask.model.LoginRequest;

@Repository
public interface LoginRequestRepository extends JpaRepository<LoginRequest, Long> {

	LoginRequest findByLoginId(String loginId);

}
