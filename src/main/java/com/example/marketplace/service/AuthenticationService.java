package com.example.marketplace.service;

import com.example.marketplace.dto.AuthenticationRequest;
import com.example.marketplace.dto.AuthenticationResponse;
import com.example.marketplace.dto.RegisterRequest;
import com.example.marketplace.entity.User;
import com.example.marketplace.repository.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        var user = User
                .builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepo.save(user);



        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .email(user.getEmail())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {


        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepo.findByEmail(request.getEmail()).orElseThrow();

        /*Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("roles", user.getRole());*/

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .email(user.getEmail())
                .build();
    }

}
