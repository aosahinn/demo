package com.preschool.demo.service.user;

import com.preschool.demo.common.exceptions.BadRequestException;
import com.preschool.demo.common.exceptions.UserNotFoundException;
import com.preschool.demo.controller.mapper.UserMapper;
import com.preschool.demo.data.entity.user.SecurityUser;
import com.preschool.demo.data.entity.user.User;
import com.preschool.demo.data.repository.BaseRepository;
import com.preschool.demo.data.repository.user.UserRepository;
import com.preschool.demo.service.AbstractEntityService;
import com.preschool.demo.type.AuthorityType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class UserService extends AbstractEntityService<User, String> implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public BaseRepository<User, String> getRepository() {
        return userRepository;
    }

    @Override
    protected User verifySave(User entity) {
        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        return super.verifySave(entity);
    }

    @Override
    protected User verifyPut(User theReal, User forSave) {
        return super.verifyPut(theReal, forSave);
    }

    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.stream().findFirst().orElseThrow(() -> new UserNotFoundException("Kullanıcı Bulunamadı."));
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        String lowercaseLogin = StringUtils.trim(login);
        Optional<User> userFromDatabase = findByUsername(lowercaseLogin);
        return userFromDatabase
                .map(theReal -> {
                    if (!theReal.isActive()) {
                        throw new BadRequestException("User " + lowercaseLogin + " was not activated");
                    }
                    //deleteAllTokens(theReal.getUsername());
                    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
                    grantedAuthorities.add(new SimpleGrantedAuthority(AuthorityType.Names.USER));
                    if (CollectionUtils.isNotEmpty(theReal.getAuthorities())) {
                        Set<SimpleGrantedAuthority> userAuthorities = theReal.getAuthorities()
                                .stream()
                                .map(authority -> new SimpleGrantedAuthority(authority.getCode()))
                                .collect(Collectors.toSet());
                        grantedAuthorities.addAll(userAuthorities);
                    }
                    if (CollectionUtils.isNotEmpty(theReal.getRoles())) {
                        Set<GrantedAuthority> roleAuthorities = new HashSet<>();
                        theReal.getRoles()
                                .stream()
                                .filter(r -> CollectionUtils.isNotEmpty(r.getAuthorities()))
                                .forEach(r -> {
                                    Set<SimpleGrantedAuthority> collect = r.getAuthorities().stream().map(a -> new SimpleGrantedAuthority(a.getCode())).collect(Collectors.toSet());
                                    roleAuthorities.addAll(collect);
                                });
                        grantedAuthorities.addAll(roleAuthorities);
                    }

                    if (grantedAuthorities.contains(new SimpleGrantedAuthority(AuthorityType.SUPER_USER.getName()))) {
                        grantedAuthorities = AuthorityType.getSuperUserAuthorities().stream().map(t -> new SimpleGrantedAuthority(t.name())).collect(Collectors.toSet());
                    } else if (grantedAuthorities.contains(new SimpleGrantedAuthority(AuthorityType.ADMIN.getName()))) {
                        grantedAuthorities = AuthorityType.getAdminAuthorities().stream().map(t -> new SimpleGrantedAuthority(t.name())).collect(Collectors.toSet());
                    }

                    SecurityUser securityUser = new SecurityUser(lowercaseLogin, theReal.getPassword(), theReal.isActive(), true, true, true, grantedAuthorities);
                    securityUser.setIdentifier(theReal.getIdentifier());
                    securityUser.setUserType(theReal.getUserType());
                    securityUser.setUserId(theReal.getIdentifier());
                    return securityUser;
                })
                .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
    }
}
