package com.preschool.demo.data.repository.user;

import com.preschool.demo.data.entity.user.User;
import com.preschool.demo.data.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, String> {

    Optional<User> findByUsername(String name);

    Optional<User> findByEmail(String email);

   /* class QueryGeneration extends BaseRepository.QueryGeneration {

        public static Specification<User> search(UserSearchDto dto) {
            return (root, query, cb) -> {
                List<Predicate> predicates = generatePredicates(cb, query, root, dto);
                query.distinct(true);
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            };
        }

        public static List<Predicate> generatePredicates(CriteriaBuilder cb, CriteriaQuery query, Root<User> root, UserSearchDto dto) {
            List<Predicate> predicates = getPredicateArray();
            predicates.add(usernamePredicate(cb, root, dto.getUsername()));
            predicates.add(equalPredicate(cb, root, "msisdn", dto.getMsisdn()));
            predicates.add(equalPredicate(cb, root, "email", dto.getEmail()));
            predicates.add(likePredicate(cb, root, "firstName", dto.getFirstName()));
            predicates.add(likePredicate(cb, root, "lastName", dto.getLastName()));

            if (!dto.isSuperUser()) {
                predicates.add(cb.not(usernamePredicate(cb, root, InitUserService.SUPER_USERNAME)));
            }

            if (StringUtils.isNotBlank(dto.getSearchText())) {
                List<Predicate> orPredicates = getPredicateArray();
                orPredicates.add(likePredicate(cb, root, "firstName", dto.getSearchText()));
                orPredicates.add(likePredicate(cb, root, "lastName", dto.getSearchText()));
                orPredicates.add(likePredicate(cb, root, "username", dto.getSearchText()));
                predicates.add(cb.or(orPredicates.toArray(new Predicate[0])));
            }


            return predicates;
        }
    }
*/
}
