package com.preschool.demo.data.repository.user;

import com.preschool.demo.data.entity.user.Role;
import com.preschool.demo.data.entity.user.UserRoles;
import com.preschool.demo.data.repository.BaseRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<Role, String> {

    Optional<Role> findByName(String name);

    class QueryGeneration extends BaseRepository.QueryGeneration {
        public static Specification<Role> search(String name, String userId) {
            return (root, query, cb) -> {
                List<Predicate> predicates = getPredicateArray();
                predicates.add(nameLikePredicate(cb, root, name));

                if (StringUtils.isNotBlank(userId)) {
                    Subquery<UserRoles> subquery = query.subquery(UserRoles.class);
                    Root<UserRoles> subRoot = subquery.from(UserRoles.class);
                    subquery.select(subRoot);
                    List<Predicate> subQueryPredicates = getPredicateArray();
                    subQueryPredicates.add(cb.equal(subRoot.get("userId"), userId));
                    subQueryPredicates.add(cb.equal(subRoot.join("role").get("identifier"), root.get("identifier")));
                    subquery.where(subQueryPredicates.toArray(new Predicate[]{}));
                    predicates.add(cb.exists(subquery));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            };
        }
    }
}
