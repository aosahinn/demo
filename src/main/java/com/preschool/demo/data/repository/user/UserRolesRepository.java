package com.preschool.demo.data.repository.user;

import com.preschool.demo.data.entity.user.Role;
import com.preschool.demo.data.entity.user.UserRoles;
import com.preschool.demo.data.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRolesRepository extends BaseRepository<UserRoles, String> {

    List<UserRoles> findByUserId(String userId);

    Optional<UserRoles> findByUserIdAndRole(String userId, Role role);

    @Transactional
    @Modifying
    void deleteByUserId(String userId);

    boolean existsByRole(Role role);

}
