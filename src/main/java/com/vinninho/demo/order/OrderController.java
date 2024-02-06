package com.vinninho.demo.order;

import com.vinninho.demo.customer.Customer;
import com.vinninho.demo.customer.CustomerNotFound;
import com.vinninho.demo.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;



    @PostMapping("/new")
    public String addOrder(
            @RequestParam String name,
            @RequestParam double price,
            @RequestParam Long customerId,
            @RequestParam Optional<Double> quantity){
        Optional<Customer> customer = customerRepository.findById(customerId);
        customer.ifPresentOrElse(
                value -> {
                    Order order = new Order(name, price, quantity.orElse(1.0), value);
                    orderRepository.save(order);

                },
                () -> {
                    throw new CustomerNotFound(customerId);
                }
        );

        return "Saved";

    }

    @GetMapping("/list")
    public Iterable<Order> listOrders(){
        return orderRepository.findAll();
    }
    @GetMapping("/list-with-customer")
    public Iterable<Order> listOrdersWithCustomer(){
        return orderRepository.findAllWithCustomer();
    }

    @GetMapping("/list/{customerId}")
    public Iterable<Order> listOrdersByCustomerId(@PathVariable Long customerId){
        return orderRepository.findOrdersByCustomerId(customerId);
    }

    @GetMapping("/find/{id}")
    public Order findOrder(@PathVariable Long id){
        return orderRepository.findOrderById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id){
        orderRepository.deleteById(id);
        return "Deleted";
    }

    @PutMapping("/update/{id}")
    public String updateOrder(@PathVariable Long id,
                              @RequestParam Optional<String> name,
                              @RequestParam Optional<Double> price,
                              @RequestParam Optional<Double> quantity,
                              @RequestParam Optional<Long> customerId){
        Order order = orderRepository.findOrderById(id);
        name.ifPresent(order::setName);
        price.ifPresent(order::setPrice);
        quantity.ifPresent(order::setQuantity);
        customerId.ifPresent(aLong -> order.setCustomer(customerRepository.findCustomerById(aLong)));
        orderRepository.save(order);
        return "Updated";
    }
}
