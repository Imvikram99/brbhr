package dev.apipulse.brbhr.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    // Assuming constructor injection is used for other beans, you could add UserRepository and PasswordEncoder here
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserRegistrationRequest registrationRequest) {
        Map<String, Object> response = new HashMap<>();
        try {
            log.info("Attempting to register new user: {}", registrationRequest.getUsername());

            // Check if user already exists
            Optional<User> existingUser = userRepository.findByUsername(registrationRequest.getUsername());

            if (existingUser.isPresent()) {
                response.put("success", false);
                response.put("message", "Username is already taken!");
                log.warn("User registration failed. Username {} is already taken.", registrationRequest.getUsername());
                return ResponseEntity.badRequest().body(response);
            }

            User newUser = new User();
            newUser.setUsername(registrationRequest.getUsername());
            newUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            // Assuming roles are passed as part of the registration request and you have a default role for new users
            Set<Role> roles = new HashSet<>();
            if(registrationRequest.getRole().equals("JOBSEEKER")) {
                roles.add(Role.ROLE_JOBSEEKER);
            }else if(registrationRequest.getRole().equals("RECUITER")){
                roles.add(Role.ROLE_RECUITER);
            }else{
                response.put("success", false);
                response.put("message", "defined role is not available!");
                return ResponseEntity.badRequest().body(response);
            }
            newUser.setRoles(roles);
            userRepository.saveUser(newUser);
            response.put("success", true);
            response.put("message", "User registered successfully!");
            log.info("User {} successfully registered.", registrationRequest.getUsername());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "User registration failed!");
            log.error("User registration failed for user: {}", registrationRequest.getUsername(), e);
            return ResponseEntity.badRequest().body(response);
        }
    }


}
