package com.vinninho.demo.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vinninho.demo.customer.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "order_id", updatable = false, nullable = false)
    private Long id;

    private String name;
    private Double price;

    @Column(columnDefinition = "double default 1")
    private Double quantity;

    @CreationTimestamp
    private Date createdOn;


    //Customer who made the order
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Order(String name, Double price, double quantity, Customer customer) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.customer = customer;
    }

}
