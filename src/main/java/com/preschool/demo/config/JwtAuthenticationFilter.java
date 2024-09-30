package com.preschool.demo.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import static com.preschool.demo.utils.TokenUtils.*;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final AuthenticationManager authManager;
    //private TokenService tokenService;
    //private JwtHelper jwtHelper;
    //private JwtUtil jwtUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            String username = request.getHeader(USERNAME);
            String password = request.getHeader(PASSWORD);
            return authManager.authenticate(new UsernamePasswordAuthenticationToken(
                    username,
                    password
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication auth) throws IOException {
        User user = (User) auth.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("demo-secret-test".getBytes());

        String accesToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(Date.from(Instant.now().plus(30, ChronoUnit.MINUTES)))
                .withIssuer(request.getRequestURI())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(Date.from(Instant.now().plus(30, ChronoUnit.MINUTES)))
                .withIssuer(request.getRequestURI())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        new ObjectMapper().writeValue(response.getOutputStream(), Map.of(TOKEN_NAME, accesToken, REFRESH_TOKEN, refreshToken));
    }

/*
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication auth) {
        try {
            SecurityContextHolder.getContext().setAuthentication(auth);
            // Generate secret key.
            SecretKey secretKey = this.jwtUtil.generateKey();
            // Create token.
            JwtModel model = this.jwtHelper.generateAccessToken(((User) auth.getPrincipal()).getUsername(), secretKey);
            // Set token.
            this.tokenService.setSecretKey(model.getToken(), model);
            // Set key expiration on redis.
            this.tokenService.setKeyExpiration(model.getToken(), model.getExpDate());
            // Add token to authorization header.
            response.addHeader(JwtConstant.AUTHORIZATION_HEADER_STRING, JwtConstant.TOKEN_BEARER_PREFIX + model.getToken());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

/*
    private AuthRequest getCredentials(HttpServletRequest request) {
        // Map dto value.
        AuthRequest auth = null;
        try {
            auth = new ObjectMapper().readValue(request.getInputStream(), AuthRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return auth;
    }
*/
}
