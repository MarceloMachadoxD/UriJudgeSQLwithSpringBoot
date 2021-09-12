package com.devsuperior.uri2602.repositories;

import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerNameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(nativeQuery = true, value = "SELECT name " +
        "from customers " +
        "where UPPER(state) = UPPER(:state)")
    List<CustomerNameProjection> search1(String state);

}
