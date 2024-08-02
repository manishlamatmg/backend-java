package com.elderlyCare.api.customer.repo;

import com.elderlyCare.api.customer.entity.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfo, String> {

    @Query("select ci from CustomerInfo ci where ci.id= :id ")
    Optional<CustomerInfo> findById(@Param("id") String id);


}
