package com.vinninho.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Customer {
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private Long id;

        private String firstName;
        private String lastName;


        public Customer(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
}
