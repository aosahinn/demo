package com.preschool.demo.service.security;

import com.preschool.demo.type.AuthorityType;
import com.preschool.demo.type.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SecurityService {

    //private final TokenStore tokenStore;

/*    public Map<String, Object> getCurrentUserAsMap() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getDetails()) {
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());

            return accessToken.getAdditionalInformation();
        } else {
            return null;
        }

    }*/

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user
     */
    public String getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                userName = springSecurityUser.getUsername();
            } else if (authentication.getPrincipal() instanceof String) {
                userName = (String) authentication.getPrincipal();
            }
        }
        return userName;
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise
     */
    public boolean isAuthenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            if (authorities != null) {
                for (GrantedAuthority authority : authorities) {
                    if (authority.getAuthority().equals("ROLE_ANONYMOUS")) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * User {@link SecurityService} hasAuthorities method.
     * If the current user has a specific authority (security role).
     * <p>
     * <p>The name of this method comes from the isUserInRole() method in the Servlet API</p>
     *
     * @param authority the authority to check
     * @return true if the current user has the authority, false otherwise
     */
    @Deprecated
    public boolean isCurrentUserInRole(String authority) {
        Set<String> currentUserAuthorities = getCurrentUserAuthorities();
        return currentUserAuthorities != null && currentUserAuthorities.contains(authority);
    }


    public boolean hasAnyAuthorities(AuthorityType... authorityTypes) {
        final Set<String> givenAuthorities = Arrays.stream(authorityTypes).map(AuthorityType::getName).collect(Collectors.toSet());
        return Optional.ofNullable(getCurrentUserAuthorities())
                .map(currentUserAuthorities -> givenAuthorities.stream().anyMatch(currentUserAuthorities::contains))
                .orElse(false);
    }

    public boolean hasAuthorities(AuthorityType... authorityTypes) {
        final Set<String> givenAuthorities = Arrays.stream(authorityTypes).map(AuthorityType::getName).collect(Collectors.toSet());
        return Optional.ofNullable(getCurrentUserAuthorities())
                .map(currentUserAuthorities -> currentUserAuthorities.containsAll(givenAuthorities))
                .orElse(false);
    }


    public Set<String> getCurrentUserAuthorities() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            return authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet());
        }
        return null;
    }

/*
    public SecurityUser getCurrentUserAsSecurityUser() {

        Map<String, Object> userInfoMap = getCurrentUserAsMap();

        Set<String> authorities = getCurrentUserAuthorities();
        if (userInfoMap != null) {
            SecurityUser user = new SecurityUser(getCurrentUserLogin(), "n/a", CollectionUtils.isNotEmpty(authorities) ? authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()) : null);
            user.setIdentifier(toString(userInfoMap.get(UserInfoKey.IDENTIFIER)));
            user.setUserType(toUserType(userInfoMap.get(UserInfoKey.USER_TYPE)));
            user.setUserId((String) userInfoMap.get(UserInfoKey.USER_ID));
            return user;
        }

        return null;
    }*/

    private String toString(Object o) {
        return o == null ? null : o.toString();
    }

    private UserType toUserType(Object o) {
        return o == null ? null : UserType.valueOf(o.toString());
    }

}
