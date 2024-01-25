package com.vinninho.demo;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long>{

    Customer findCustomerById(Integer id);

}
