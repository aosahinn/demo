package com.preschool.demo.data.repository.user;

import com.preschool.demo.data.entity.user.Authority;
import com.preschool.demo.data.repository.BaseRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AuthorityRepository extends BaseRepository<Authority, String> {

    Optional<Authority> findByCode(String code);

    class QueryGeneration extends BaseRepository.QueryGeneration {
        public static Specification<Authority> search(Set<String> excludeCodes) {
            return (root, query, cb) -> {
                List<Predicate> predicates = getPredicateArray();
                predicates.add(codesExcludePredicate(cb, root, excludeCodes));
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            };
        }
    }
}
