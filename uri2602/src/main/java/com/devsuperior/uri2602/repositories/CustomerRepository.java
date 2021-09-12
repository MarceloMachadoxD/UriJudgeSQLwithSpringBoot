package com.devsuperior.uri2602.repositories;

import com.devsuperior.uri2602.DTO.CustomerNameDTO;
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


    @Query("SELECT new com.devsuperior.uri2602.DTO.CustomerNameDTO(obj.name) " +
        " from Customer obj " +
        " WHERE UPPER(obj.state) = UPPER(:state)")
    List<CustomerNameDTO> search2(String state);

}
