package com.rammy.project.uber.uberApp.services;

import com.rammy.project.uber.uberApp.entities.User;
import com.rammy.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.rammy.project.uber.uberApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public final class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("User with email" + username +"not found")
                );
    }
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("User not found with id: "+id)
        );
    }
}
