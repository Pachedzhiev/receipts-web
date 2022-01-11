package com.example.demo.services;

import com.example.demo.services.models.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserServiceInt extends UserDetailsService {
    void register(UserServiceModel user);
}
