package com.nexus.ecommerce.service;

import com.nexus.ecommerce.dto.AuthenticationRequest;
import com.nexus.ecommerce.dto.AuthenticationResponse;
import com.nexus.ecommerce.dto.RegistrationRequest;
import com.nexus.ecommerce.model.User;
import com.nexus.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegistrationRequest request){
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse login(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        var user = userRepository.findByUsername(request.username())
                .orElseThrow(()->new IllegalStateException("User not found after authentication"));
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
