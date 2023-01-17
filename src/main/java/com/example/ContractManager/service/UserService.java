package com.example.ContractManager.service;

import com.example.ContractManager.model.Role;
import com.example.ContractManager.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface UserService extends UserDetailsService {
    User findByLogin(String login);
    Set<Role> findAllRoles();
    void saveUser(User user);
    List<User> findAll();
    void deleteUser(Long id);
}
