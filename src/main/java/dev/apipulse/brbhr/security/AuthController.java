package dev.apipulse.brbhr.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@Log4j2
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthenticationRequest authenticationRequest, HttpServletRequest request) {

        try {

            String username = authenticationRequest.getUsername();
            log.info("Attempting to authenticate user: {}", username);

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authenticationRequest.getPassword()));
            log.info("Authentication successful for user: {}", username);
            List<String> authorities;
            if (authentication != null && authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
                authorities = authentication.getAuthorities().stream()
                        .map(x->x.getAuthority())
                        .collect(Collectors.toList());
            } else {
                authorities = Collections.emptyList();
            }
            String token = jwtTokenProvider.createToken(username, authorities);
            log.info("Generated JWT for user: {}", username);

            Map<String, String> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            log.info("Sending response with JWT for user: {}", username);

            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            log.error("Authentication failed for user: {}", authenticationRequest.getUsername());
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }


}
