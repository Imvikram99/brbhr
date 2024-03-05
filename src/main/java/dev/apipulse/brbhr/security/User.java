package dev.apipulse.brbhr.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    private String id;
    private String username;
    private String password;
    private Set<Role> roles = new HashSet<>();

    public User(String username, Set<Role> roles) {
        this.username = username;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Update based on your requirements
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Update based on your requirements
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Update based on your requirements
    }

    @Override
    public boolean isEnabled() {
        return true; // Update based on your requirements
    }
}
