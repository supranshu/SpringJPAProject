//package com.apitask.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.apitask.model.LoginRequest;
//import com.apitask.repositories.LoginRequestRepository;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private LoginRequestRepository userRepository; // Replace with your UserRepository or data access class
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Retrieve user details from your data source (e.g., database) using UserRepository
//        LoginRequest user = userRepository.findByUsername(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//
//        // Create a UserDetails object with user details and authorities
//        // You can customize this based on your user model and authentication requirements
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getLogin_id())
//                .password(user.getPassword()) // This should be the hashed pa // Add user roles/authorities here as needed
//                .accountExpired(false)
//                .accountLocked(false)
//                .credentialsExpired(false)
//                .disabled(false)
//                .build();
//    }
//}
//
