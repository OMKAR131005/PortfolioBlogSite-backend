package com.omkar.porfolio_backend.controllers;

import com.omkar.porfolio_backend.Entity.LoginRequest;
import com.omkar.porfolio_backend.Entity.Roles;
import com.omkar.porfolio_backend.Entity.User;
import com.omkar.porfolio_backend.Repository.UserRepository;
import com.omkar.porfolio_backend.Security.JwtUtil;
import com.omkar.porfolio_backend.dto.ApiResponse;
import com.omkar.porfolio_backend.dto.ChatRequest;
import com.omkar.porfolio_backend.services.GeminiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Authentication endpoints")
public class AuthController {
     private final AuthenticationManager authenticationManager;
    private final GeminiService geminiService;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    @Operation(summary = "Admin login — returns JWT token")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401)
                    .body(ApiResponse.error("Invalid username or password"));
        }

        String token = JwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(ApiResponse.success("Login successful", token));
    }


}
