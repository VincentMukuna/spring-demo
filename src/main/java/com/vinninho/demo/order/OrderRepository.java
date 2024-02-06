package com.vinninho.demo.order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
        Order findOrderById(Long id);
        List<Order> findOrdersByCustomerId(Long customerId);

        @Query("SELECT o FROM Order o JOIN FETCH o.customer")
        List<Order> findAllWithCustomer();
}
