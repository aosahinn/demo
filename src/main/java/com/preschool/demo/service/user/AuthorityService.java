package com.preschool.demo.service.user;

import com.preschool.demo.data.entity.user.Authority;
import com.preschool.demo.data.repository.BaseRepository;
import com.preschool.demo.data.repository.user.AuthorityRepository;
import com.preschool.demo.service.AbstractEntityService;
import com.preschool.demo.service.security.SecurityService;
import com.preschool.demo.type.AuthorityType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthorityService extends AbstractEntityService<Authority, String> {

    private final AuthorityRepository authorityRepository;

    private final SecurityService securityService;

    @Override
    public BaseRepository<Authority, String> getRepository() {
        return authorityRepository;
    }

    public Optional<Authority> findByCode(String code) {
        return authorityRepository.findByCode(code);
    }

    public Authority findByType(AuthorityType type) {
        return authorityRepository.findByCode(type.getName()).orElse(null);
    }


    public Page<Authority> search(Pageable pageable) {
        Set<String> excludeCodes = new HashSet<>();
        if (!securityService.hasAuthorities(AuthorityType.SUPER_USER)) {
            excludeCodes.addAll(AuthorityType.getOnlySuperUserSpecificAuthorityCodesAsSet());
        }

        if (!securityService.hasAuthorities(AuthorityType.ADMIN)) {
            excludeCodes.addAll(AuthorityType.getOnlyAdminSpecificAuthorityCodesAsSet());
        }

        return authorityRepository.findAll(AuthorityRepository.QueryGeneration.search(excludeCodes), pageable);
    }
}
