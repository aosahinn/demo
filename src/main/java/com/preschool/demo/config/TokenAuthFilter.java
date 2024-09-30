/*
package com.preschool.demo.config;

import com.preschool.demo.type.AuthorityType;
import com.preschool.demo.utils.TokenUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.preschool.demo.utils.TokenUtils.*;


public class TokenAuthFilter extends OncePerRequestFilter {

    private static final GrantedAuthority SUPER_USER_AUTHORITY = new SimpleGrantedAuthority(AuthorityType.Names.SUPER_USER);
    private TokenService tokenService;
    private long expireTime;
    private RoleService roleService;
    private UserAgencyService userAgencyService;

    private String jwtSigningKey;

    private AntPathMatcher pathMatcher = new AntPathMatcher();


    public TokenAuthFilter(TokenService tokenService, long expireTime, RoleService roleService, UserAgencyService userAgencyService, String jwtSigningKey) {
        this.tokenService = tokenService;
        this.expireTime = expireTime;
        this.roleService = roleService;
        this.userAgencyService = userAgencyService;
        this.jwtSigningKey = jwtSigningKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain filterChain) throws ServletException, IOException {

        String authenticationToken = httpRequest.getHeader(TOKEN_NAME);
        String agencyCode = httpRequest.getHeader(AGENCY_CODE);

        authenticationToken = TokenUtils.decodeBase64(authenticationToken);
        Token token = tokenService.findByAccessToken(authenticationToken);
        String requestUri = httpRequest.getRequestURI();

        if (shouldTokenChecked(token, requestUri)) {
            if (isTokenNotExpired(token)) {
                User user = token.getUser();

                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(user.getUserLevel().toString()));

                Set<Role> roles = roleService.getUserRoles(user.getId());
                if (CollectionUtils.isNotEmpty(roles)) {
                    Set<SimpleGrantedAuthority> collect = roles.stream()
                            .filter(Objects::nonNull)
                            .filter(role -> CollectionUtils.isNotEmpty(role.getAuthorities()))
                            .flatMap(r -> r.getAuthorities().stream())
                            .map(a -> new SimpleGrantedAuthority(a.getCode()))
                            .collect(Collectors.toSet());
                    authorities.addAll(collect);
                }
                if (StringUtils.isNoneBlank(agencyCode)
                        && !authorities.contains(SUPER_USER_AUTHORITY)
                        && !userAgencyService.checkAgency(user.getUsername(), agencyCode)) {
                    writeError(httpResponse, ResponseStatus.AGENCY_NOT_FOUND.value(), "Agent not found");
                } else {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, user.getPassword(), authorities);
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    filterChain.doFilter(httpRequest, httpResponse);
                }
            } else {
                writeError(httpResponse, ResponseStatus.ACCESS_TOKEN_EXPIRED.value(), "Access token is expired");
            }
        } else {
            String accessToken = httpRequest.getHeader(ACCESS_TOKEN);

            if (StringUtils.isNotBlank(accessToken)) {
                DecodedJWT verify = JWT.require(Algorithm.HMAC512(jwtSigningKey.getBytes())).build().verify(accessToken);
                String jwtAgencyCode = verify
                        .getClaim(AGENCY_CODE).asString();

                if (StringUtils.equals(agencyCode, jwtAgencyCode)) {
                    String username = verify.getSubject();
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, "n/a", Collections.singleton(SERVICE_AUTHORITY));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    filterChain.doFilter(httpRequest, httpResponse);
                } else {
                    writeError(httpResponse, ResponseStatus.INVALID_ACCESS_TOKEN.value(), "Invalid Access Token");
                }


            } else {
                filterChain.doFilter(httpRequest, httpResponse);
            }


        }


    }

    public void writeError(ServletResponse httpResponse, String statusCode, String message) throws IOException {
        HttpServletResponse response = (HttpServletResponse) httpResponse;
        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().write("{\"metadata\":{\"status\":\"" + statusCode + "\",\"message\":\"" + message + "\"}}");
    }

    private boolean isTokenNotExpired(Token token) {
        return token != null && token.getCreatedDate().plus(expireTime, ChronoUnit.MILLIS).compareTo(ZonedDateTime.now()) >= 0;
    }

    */
/**
     * Returns true if token is not null and requestUri not equals /api/refresh-token
     * If the requestUri equals /api/refresh-token, token should not be checked for expiration
     * since user already knows the token is expired and wants to refresh it when making a request to
     * "refresh-token" endpoint.
     *//*

    private boolean shouldTokenChecked(Token token, String requestUri) {
        return token != null && !"/api/refresh-token".equals(requestUri);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return Stream.of(IgnoredUriUtils.getIgnoredMatchers()).anyMatch(p -> pathMatcher.match(p, request.getRequestURI()));
    }
}
*/
