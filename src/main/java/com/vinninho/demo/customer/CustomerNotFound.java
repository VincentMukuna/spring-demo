package com.vinninho.demo.customer;

public class CustomerNotFound extends RuntimeException{
    public CustomerNotFound(Long id){
        super("Could not find customer " + id);
    }
}
