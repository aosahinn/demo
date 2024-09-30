package com.preschool.demo.service.user;

import com.preschool.demo.common.exceptions.BadRequestException;
import com.preschool.demo.data.entity.user.Authority;
import com.preschool.demo.data.entity.user.Role;
import com.preschool.demo.data.entity.user.UserRoles;
import com.preschool.demo.data.repository.BaseRepository;
import com.preschool.demo.data.repository.user.RoleRepository;
import com.preschool.demo.data.repository.user.UserRolesRepository;
import com.preschool.demo.service.AbstractEntityService;
import com.preschool.demo.type.AuthorityType;
import com.preschool.demo.type.MessageType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.preschool.demo.type.MessageType.NAME_ALREADY_DEFINED;
import static com.preschool.demo.type.MessageType.NAME_CAN_NOT_BLANK;

@Service
@RequiredArgsConstructor
public class RoleService extends AbstractEntityService<Role, String> {

    private final RoleRepository repository;

    private final UserRolesRepository userRolesRepository;

    private final AuthorityService authorityService;

    @Override
    public BaseRepository<Role, String> getRepository() {
        return repository;
    }

    public Optional<Role> findByName(String name) {
        return repository.findByName(name);
    }


    @Override
    protected Role verifyDelete(Role entity) {
        if (userRolesRepository.existsByRole(entity)) {
            throw new BadRequestException(MessageType.ROLE_IN_USED_CAN_NOT_DELETE.getKey());
        }
        return entity;
    }

    @Override
    protected Role verifySave(Role entity) {
        return checkEntity(entity, true);
    }

    @Override
    protected Role verifyPut(Role theReal, Role forSave) {
        return checkEntity(forSave, false);
    }

    private Role checkEntity(Role entity, boolean isPost) {
        if (isPost) {
            entity.setIdentifier(null);
        }
        if (StringUtils.isBlank(entity.getName())) {
            throw new BadRequestException(NAME_CAN_NOT_BLANK.getKey());
        }

        this.findByName(entity.getName())
                .ifPresent(role -> {
                    if (isPost || !entity.getIdentifier().equalsIgnoreCase(role.getIdentifier())) {
                        //throw new DuplicateResourceException(NAME_ALREADY_DEFINED.getKey(), entity.getName());
                        throw new BadRequestException(NAME_ALREADY_DEFINED.getKey());
                    }
                });
        if (CollectionUtils.isNotEmpty(entity.getAuthorities())) {
            Set<Authority> collect = entity.getAuthorities().stream()
                    .filter(authority -> StringUtils.isNotBlank(authority.getIdentifier()))
                    .map(authority -> authorityService.getEntity(authority.getIdentifier()))
                    .collect(Collectors.toSet());
            entity.setAuthorities(collect);
        }

        return entity;
    }

    public Page<Role> search(String name, String userId, Pageable pageable) {
        return repository.findAll(RoleRepository.QueryGeneration.search(name, userId), pageable);
    }

    public List<Role> search(String name, String userId) {
        return repository.findAll(RoleRepository.QueryGeneration.search(name, userId));
    }

    public Set<Role> getUserRoles(String userId) {
        List<UserRoles> roles = userRolesRepository.findByUserId(userId);
        if (CollectionUtils.isNotEmpty(roles)) {
            return roles.stream().map(UserRoles::getRole).collect(Collectors.toSet());
        } else {
            return new HashSet<>();
        }
    }

    @Transactional
    public void addBulkRole(String userId, Set<String> roleIds) {
        userRolesRepository.deleteByUserId(userId);
        List<UserRoles> collect = roleIds.stream()
                .map(this::getEntity)
                .map(role -> new UserRoles(userId, role))
                .collect(Collectors.toList());
        userRolesRepository.flush();
        userRolesRepository.saveAll(collect);
    }

    public Role addOrRemoveUserRole(String userId, String roleId, boolean isAdd) {
        Role role = getEntity(roleId);
        UserRoles theReal = userRolesRepository.findByUserIdAndRole(userId, role)
                .orElse(new UserRoles(userId, role));
        if (isAdd) {
            theReal = userRolesRepository.save(theReal);
        } else {
            if (StringUtils.isNotBlank(theReal.getIdentifier())) {
                userRolesRepository.delete(theReal);
            }
        }
        return theReal.getRole();
    }

    /**
     * Add Platform Admin, Default roles.
     */
    @Transactional
    public List<Role> initPredefinedRoles(Set<Authority> authorities) {
        List<Role> all = repository.findAll();
        if (CollectionUtils.isEmpty(all)) {
            Set<Authority> adminAuthorities = authorities.stream()
                    .filter(a -> AuthorityType.getAdminAuthorities().contains(AuthorityType.valueOf(a.getCode())))
                    .collect(Collectors.toSet());
            Role adminRole = new Role("Admin", new HashSet<>(adminAuthorities));

            Set<Authority> defaultAuthorities = authorities.stream()
                    .filter(a -> AuthorityType.getDefaultAuthorities().contains(AuthorityType.valueOf(a.getCode())))
                    .collect(Collectors.toSet());
            Role defaultRole = new Role("Default", defaultAuthorities);

            List<Role> roles = new ArrayList<>();
            roles.add(adminRole);
            roles.add(defaultRole);
            return repository.saveAll(roles);
        }
        return all;

    }

}
