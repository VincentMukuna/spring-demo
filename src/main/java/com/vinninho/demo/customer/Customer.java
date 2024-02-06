package com.vinninho.demo.customer;

import com.vinninho.demo.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@Getter
@Setter
public class Customer {
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        @Column(name = "customer_id", updatable = false, nullable = false)
        private Long id;

        private String firstName;
        private String lastName;


        public Customer(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
}
