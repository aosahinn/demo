package com.preschool.demo.service.init;

import com.preschool.demo.data.entity.user.Authority;
import com.preschool.demo.data.entity.user.User;
import com.preschool.demo.service.user.AuthorityService;
import com.preschool.demo.service.user.RoleService;
import com.preschool.demo.service.user.UserService;
import com.preschool.demo.type.AuthorityType;
import com.preschool.demo.type.UserType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.ap.internal.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class InitUserService implements ApplicationRunner {

    public static final String SUPER_USERNAME = "superUser";

    private static final Logger LOG = LoggerFactory.getLogger(InitUserService.class);

    private final AuthorityService authorityService;

    private final RoleService roleService;

    private final UserService userService;

    private final Environment environment;
/*

    @Autowired
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
*/

    @Async
    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        Set<Authority> authorities = addAuthority(AuthorityType.values());
        addManagerUsers(authorities);

        roleService.initPredefinedRoles(authorities);

        if (ArrayUtils.contains(environment.getActiveProfiles(), "h2")) {
            addMockUser();
        }
    }

    private Set<Authority> addAuthority(AuthorityType... authorityTypes) {
        LOG.info("=== " + "Adding Authority".toUpperCase() + "===");
        return Stream.of(authorityTypes)
                .map(r -> authorityService.findByCode(r.getName()).orElseGet(() -> authorityService.save(new Authority(r))))
                .collect(Collectors.toSet());
    }

    private void addManagerUsers(Set<Authority> authorities) {
        LOG.info("=== " + "Adding Manager User".toUpperCase() + "===");

        Authority superAuthority = authorities.stream()
                .filter(a -> a.getCode().equalsIgnoreCase(AuthorityType.SUPER_USER.getName()))
                .findAny().get();


        Authority adminAuthority = authorities.stream()
                .filter(a -> a.getCode().equalsIgnoreCase(AuthorityType.ADMIN.getName()))
                .findAny().get();

        User superUser = new User("Super", "User", SUPER_USERNAME, "12345", "super@volantx.com", true, Collections.asSet(superAuthority));
        User adminUser = new User("Admin", "User", "admin", "12345", "admin@volantx.com", true, Collections.asSet(adminAuthority));

        Stream.of(superUser, adminUser)
                .map(cdt -> userService.findByUsername(cdt.getUsername())
                        .map(u -> {
                            u.setAuthorities(cdt.getAuthorities());
                            return u;
                        })
                        .orElse(cdt)
                )
                .forEach(cdt -> {
                    if (StringUtils.isBlank(cdt.getIdentifier())) {
                        userService.save(cdt);
                    } else {
                        userService.put(cdt.getIdentifier(), cdt);
                    }
                });
    }

    private void addMockUser() {
        Authority userAuthority = authorityService.findByType(AuthorityType.USER);

        addUser("owner", UserType.OWNER, userAuthority);
        addUser("customer", UserType.CUSTOMER, userAuthority);

        for (int i = 0; i <= 9; i++) {
            addUser("o" + i, UserType.OWNER, userAuthority);
        }
        for (int i = 0; i <= 19; i++) {
            addUser("c" + i, UserType.CUSTOMER, userAuthority);
        }
    }

    private User addUser(String username, UserType userType, Authority userAuthority) {
        LOG.info("=== " + "Adding User".toUpperCase() + "===");
        User user = new User("Mock", "User", username, "12345", username + "@volantx.com", true, Collections.asSet(userAuthority));
        user.setUserType(userType);
        User cdt = userService.findByUsername(user.getUsername()).orElse(user);
        if (StringUtils.isBlank(cdt.getIdentifier())) {
            user = userService.save(user);
        } else {
            user = userService.put(cdt.getIdentifier(), cdt);
        }
        return user;
    }


}
