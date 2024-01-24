package dev.apipulse.brbhr.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Log4j2
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    private List<String> excludeUrlPatterns = Arrays.asList("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**");


    public JwtTokenAuthenticationFilter() {

    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            //TenantContext.setTenantId(partnerId);
            String token = jwtTokenProvider.resolveToken(httpServletRequest);

            if (token != null) {
                log.debug("Token: {}", token);

                if (jwtTokenProvider.validateToken(token)) {
                    Authentication auth = jwtTokenProvider.getAuthentication2(token);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    log.warn("Invalid JWT token");
                }
            }

            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } finally {
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return isSkipAbleEndpoint(path); // adjust this to the endpoint you want to exclude
    }

    public  boolean isSkipAbleEndpoint(String path) {
        return path.startsWith("/test/") || path.startsWith("/api/customer/")  || path.contains("login") || path.equalsIgnoreCase("/") || path.equalsIgnoreCase("/favicon.ico");
    }


}
