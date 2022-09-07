package com.preschool.demo.data.repository;

import com.preschool.demo.data.entity.IdEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoRepositoryBean
public interface BaseRepository<T extends IdEntity, PK extends Serializable> extends JpaRepository<T, PK>, JpaSpecificationExecutor<T> {
    public static class QueryGeneration {
        public QueryGeneration() {
        }

        public static List<Predicate> getPredicateArray() {
            return new ArrayList<Predicate>() {
                public boolean add(Predicate predicate) {
                    return predicate != null ? super.add(predicate) : false;
                }
            };
        }

        public static <S> List<S> aggregate(EntityManager em, CriteriaQuery<S> query, Root<?> root, CriteriaBuilder cb, Predicate predicate, List<Selection<?>> selectionList, List<Expression<?>> groupByList, LockModeType lockMode) {
            if (CollectionUtils.isNotEmpty(selectionList)) {
                if (predicate != null) {
                    query.where(predicate);
                }

                query.multiselect(selectionList);
                if (CollectionUtils.isNotEmpty(groupByList)) {
                    query.groupBy(groupByList);
                }

                return em.createQuery(query).setLockMode(lockMode).getResultList();
            } else {
                return null;
            }
        }

        public static Predicate tenantIdPredicate(CriteriaBuilder cb, Root<?> root, String tenantId) {
            return cb.equal(root.get("tenantId"), tenantId);
        }

        public static Predicate tenantIdsPredicate(CriteriaBuilder cb, Root<?> root, Set<String> tenantIds) {
            return cb.in(root.get("tenantId")).value(tenantIds);
        }

        public static Predicate idsPredicate(CriteriaBuilder cb, Root<?> root, Set<String> ids) {
            return inPredicate(cb, root, "identifier", ids);
        }

        public static Predicate codePredicate(CriteriaBuilder cb, Root<?> root, String code) {
            return equalPredicate(cb, root, "code", code);
        }

        public static Predicate codesExcludePredicate(CriteriaBuilder cb, Root<?> root, Set<String> excludeCodes) {
            return notInPredicate(cb, root, "code", excludeCodes);
        }

        public static Predicate nameLikePredicate(CriteriaBuilder cb, Root<?> root, String name) {
            return likePredicate(cb, root, "name", name);
        }

      /*  public static Predicate statusPredicate(CriteriaBuilder cb, Root<?> root, EntityStatus status) {
            return status == null ? null : cb.equal(root.get("status"), status);
        }*/

        public static Predicate usernamePredicate(CriteriaBuilder cb, Root<?> root, String username) {
            return equalPredicate(cb, root, "username", username);
        }

        public static Predicate equalPredicate(CriteriaBuilder cb, Root<?> root, String fieldName, String fieldValue) {
            return StringUtils.isBlank(fieldValue) ? null : cb.equal(root.get(fieldName), fieldValue);
        }

        public static Predicate likePredicate(CriteriaBuilder cb, Root<?> root, String fieldName, String fieldValue) {
            return StringUtils.isBlank(fieldValue) ? null : cb.like(cb.upper(root.get(fieldName)), "%" + fieldValue.toUpperCase() + "%");
        }

        public static Predicate inPredicate(CriteriaBuilder cb, Root<?> root, String fieldName, Set<String> fieldValues) {
            return CollectionUtils.isEmpty(fieldValues) ? null : cb.in(root.get(fieldName)).value(fieldValues);
        }

        public static Predicate notInPredicate(CriteriaBuilder cb, Root<?> root, String fieldName, Set<String> fieldValues) {
            return CollectionUtils.isEmpty(fieldValues) ? null : cb.not(cb.in(root.get(fieldName)).value(fieldValues));
        }
    }
}
