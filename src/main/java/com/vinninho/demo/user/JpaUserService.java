package com.vinninho.demo.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JpaUserService implements UserDetailsService {
    private final UserRepository userRepository;

    public JpaUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException{
       return userRepository
               .findByUsername(username)
               .map(SecurityUser::new)
               .orElseThrow(() -> new UsernameNotFoundException("User " +username+" not found"));
    }


}
