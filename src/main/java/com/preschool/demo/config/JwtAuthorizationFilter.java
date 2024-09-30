package com.preschool.demo.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.preschool.demo.common.exceptions.BadRequestException;
import com.preschool.demo.utils.enums.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private UserDetailsService userDetailsService;
    //private TokenService tokenService;
    //private JwtHelper jwtHelper;
    //private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (request.getServletPath().equals("/login")) {
            chain.doFilter(request, response);
        }
        else {
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authHeader != null && authHeader.startsWith("Bearer")) {
                try {
                    String token = authHeader.substring("Bearer".length() + 1);
                    Algorithm algorithm = Algorithm.HMAC256("demo-secret-test".getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    List<SimpleGrantedAuthority> authorities = Stream.of(decodedJWT.getClaim("roles").asArray(String.class)).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    chain.doFilter(request, response);
                } catch (Exception e) {
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), Map.of("message", e.getMessage()));
                }
            } else {
                //writeError(response, ResponseStatus.INVALID_ACCESS_TOKEN.value(), "Invalid Access Token");
                chain.doFilter(request, response);
            }
        }
    }

    public void writeError(ServletResponse httpResponse, String statusCode, String message) throws IOException {
        HttpServletResponse response = (HttpServletResponse) httpResponse;
        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().write("{\"metadata\":{\"status\":\"" + statusCode + "\",\"message\":\"" + message + "\"}}");
    }

/*    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) {
        try {
            // Check for authorization header existence.
            String header = request.getHeader(JwtConstant.AUTHORIZATION_HEADER_STRING);
            if (header == null || !header.startsWith(JwtConstant.TOKEN_BEARER_PREFIX)) {
                chain.doFilter(request, response);
                return;
            }
            // Validate request..
            UsernamePasswordAuthenticationToken authorization = authorizeRequest(request);
            SecurityContextHolder.getContext().setAuthentication(authorization);
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

/*
    private UsernamePasswordAuthenticationToken authorizeRequest(HttpServletRequest request) {
        try {
            // Get token.
            String token = this.jwtUtil.extractToken(request);
            if (token != null) {
                // Get token key.
                JwtModel model = (JwtModel) this.tokenService.getSecretKey(token);
                // Validate token.
                Claims claims = this.jwtHelper.validateToken(model.getSecretKey(), model);
                // Validate user authority/role if allowed to do the api dto.
                String user = claims.getSubject();
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(user);
                if (userDetails != null) {
                    return new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
*/
}
