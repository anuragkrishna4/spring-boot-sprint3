package com.nisum.customer.repository;

import com.nisum.customer.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    public void testFindByEmailContaining() {
        Customer c1 = new Customer(null, "Anurag", "anurag@gmail.com", LocalDate.now());
        Customer c2 = new Customer(null, "Krishna", "krishna@demo.com", LocalDate.now());
        repository.saveAll(List.of(c1, c2));

        List<Customer> result = repository.findByEmailContaining("gmail");
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Anurag");
    }

    @Test
    public void testFindByRegisteredDateAfter() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        Customer c = new Customer(null, "Ankit", "ankit34@mail.com", LocalDate.of(2025, 1, 1));
        repository.save(c);

        List<Customer> result = repository.findByRegisteredDateAfter(date);
        assertThat(result).isNotEmpty();
    }

    @Test
    public void testFindByNameJPQL() {
        Customer c = new Customer(null, "Ravi", "ravi@mail.com", LocalDate.now());
        repository.save(c);
        List<Customer> result = repository.findByNameJPQL("Ravi");
        assertThat(result).hasSize(1);
    }
}