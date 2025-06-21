package com.nisum.customer.repository;

import com.nisum.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByEmailContaining(String keyword);
    List<Customer> findByRegisteredDateAfter(LocalDate date);

    @Query("SELECT c FROM Customer c WHERE c.name = ?1")
    List<Customer> findByNameJPQL(String name);

    @Query(value = "SELECT * FROM customers WHERE name = ?1", nativeQuery = true)
    List<Customer> findByNameNative(String name);
}
