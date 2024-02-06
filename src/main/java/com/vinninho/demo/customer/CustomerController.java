package com.vinninho.demo.customer;

import com.vinninho.demo.order.Order;
import com.vinninho.demo.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController{
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public CustomerController(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;

        var customer1 = new Customer("John", "Doe");
        var customer2 = new Customer("Jane", "Doe");
        var customer3 = new Customer("John", "Smith");
        var customer4 = new Customer("Jane", "Smith");

        customerRepository.saveAll(List.of(customer1, customer2, customer3, customer4));

        var order1 = new Order("Falafel", 10.0, 1.0, customer1);
        var order2 = new Order("Fries", 20.0, 2.0, customer1);
        var order3 = new Order("Oranges", 30.0, 3.0, customer2);
        var order4 = new Order("Tahini", 40.0, 4.0, customer2);
        var order5 = new Order("Pizza", 50.0, 5.0, customer3);
        var order6 = new Order("Burger", 60.0, 6.0, customer3);
        var order7 = new Order("Pasta", 70.0, 7.0, customer4);

        orderRepository.saveAll(List.of(order1, order2, order3, order4, order5, order6, order7));

    }

    @PostMapping("/add")
    public String addCustomer(@RequestParam String firstName, @RequestParam String lastName) {
        Customer customer = new Customer(firstName, lastName);
        customerRepository.save(customer);
        return "Saved";
    }

    @GetMapping("/list")
    public Iterable<Customer> listCustomers() {
        return customerRepository.findAll();
    }


    @GetMapping("/find/{id}")
    public Customer findCustomer(@PathVariable Long id) {
        return customerRepository.findCustomerById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return "Deleted";
    }

    @PutMapping("/update/{id}")
    public String updateCustomer(@PathVariable Long id, @RequestParam Optional<String> firstName, @RequestParam Optional<String> lastName) {
        Customer customer = customerRepository.findCustomerById(id);
        firstName.ifPresent(customer::setFirstName);
        lastName.ifPresent(customer::setLastName);
        customerRepository.save(customer);
        return "Updated";
    }



}
