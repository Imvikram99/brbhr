package dev.apipulse.brbhr.controller;


import dev.apipulse.brbhr.model.LoginRequest;
import dev.apipulse.brbhr.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Permission;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

//    @PostMapping("/login")
//    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
//        TokenResponse token = authService.authenticate(loginRequest);
//        return ResponseEntity.ok(token);
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<Void> logout(@RequestBody LogoutRequest logoutRequest) {
//        authService.logout(logoutRequest);
//        return ResponseEntity.noContent().build();
//    }

    @GetMapping("/permissions")
    public ResponseEntity<List<Permission>> getPermissions(@RequestParam String userId) {
        List<Permission> permissions = authService.getPermissions(userId);
        return ResponseEntity.ok(permissions);
    }
}
