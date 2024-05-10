package com.backend.backend.service;

import com.backend.backend.model.User;
import com.backend.backend.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ResponseEntity<Map<String, Object>> createUser(String username, String password, String email) {
        Map<String, Object> map = new LinkedHashMap<>();
        try {

            Optional<User> existingUser = userRepository.findByEmail(email);
            if (existingUser.isPresent()) {
                map.put("status", 1);
                map.put("response", "User with this email already exists");
                return new ResponseEntity<>(map, BAD_REQUEST);
            }

            User user = new User(username, password, email);
            userRepository.save(user);
            map.put("status", 0);
            map.put("response", "user create successfully");
            return new ResponseEntity<>(map, CREATED);
        } catch (EntityExistsException e) {
            map.put("status", 1);
            map.put("response", "Failed to create user: " + e.getMessage());
            return new ResponseEntity<>(map, BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String, Object>> login(String email, String password) {
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            User user = new User(email, password);
            Optional<User> existingUser = userRepository.findByEmail(email);
            if (existingUser.isPresent()) {
                if(existingUser.get().getEmail().equalsIgnoreCase(email) && existingUser.get().getPassword().equals(password)) {
                    map.put("status", 0);
                    map.put("response", existingUser.get());
                    return new ResponseEntity<>(map, CREATED);
                } else {
                    map.put("status", 1);
                    map.put("response", "invalid email or password");
                    return new ResponseEntity<>(map, BAD_REQUEST);
                }
            } else {
                map.put("status", 1);
                map.put("response", "user not found with the given email");
                return new ResponseEntity<>(map, BAD_REQUEST);
            }
        } catch(Exception e) {
            map.clear();
            map.put("status", 1);
            map.put("response", "Failed to login");
            return new ResponseEntity<>(map, BAD_REQUEST);
        }
    }
}

