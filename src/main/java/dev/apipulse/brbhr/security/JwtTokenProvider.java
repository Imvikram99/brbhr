package dev.apipulse.brbhr.security;

import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j2
public class JwtTokenProvider {

    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;
    @Value("${security.jwt.token.expire-length}")
    private long validityInMilliseconds; // 24h

    public String createToken(String username, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        // Add log statement
        log.debug("Created JWT token for user: {}", username);

        return token;
    }

    public Authentication getAuthentication(String token) {
        String username = getUsername(token);
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Authentication getAuthentication2(String token) {
        Claims claims = extractClaims(token);
        String email = extractEmail(claims);
        List<String> roleNames = extractRoles(claims);
        Set<Role> userRoles = convertToRoleSet(roleNames);
        UserDetails userDetails = new User(email, userRoles);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Set<Role> convertToRoleSet(List<String> roleNames) {
        return roleNames.stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }

    public String getUsername(String token) {
        String username = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();

        // Add log statement
        log.debug("Extracted username '{}' from JWT token", username);

        return username;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);

            // Add log statement
            log.debug("Resolved JWT token: {}", token);

            return token;
        }
        return null;
    }

    public Claims extractClaims(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);

        Claims claims = claimsJws.getBody();

        return claims;
    }

    public String extractEmail(Claims claims) {
        return claims.getSubject(); // Assuming the email is stored as the subject
    }

    public List<String> extractRoles(Claims claims) {
        return claims.get("roles", List.class); // Assuming the roles are stored under the "roles" key
    }
}
