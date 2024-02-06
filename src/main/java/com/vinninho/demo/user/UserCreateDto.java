package com.vinninho.demo.user;

import lombok.*;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserCreateDto {
    private String username;
    private String password;
}

