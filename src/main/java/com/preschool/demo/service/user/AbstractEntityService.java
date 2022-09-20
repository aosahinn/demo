package com.preschool.demo.service.user;

import com.preschool.demo.common.exceptions.ResourceNotFoundException;
import com.preschool.demo.data.entity.BaseEntity;
import com.preschool.demo.data.repository.BaseRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractEntityService<T extends BaseEntity, PK extends Serializable> {
    protected static final Logger LOG = LoggerFactory.getLogger(AbstractEntityService.class);

    public AbstractEntityService() {
    }

    public abstract BaseRepository<T, PK> getRepository();

    protected T verifySave(T entity) {
        return entity;
    }

    protected T verifyPut(T theReal, T forSave) {
        return forSave;
    }

    protected T verifyDelete(T entity) {
        return entity;
    }

    @Transactional
    public T save(T entity) {
        this.verifySave(entity);
        return (T) this.getRepository().save(entity);
    }

    @Transactional
    public T put(PK id, T forSave) {
        T theReal = this.getEntity(id);
        forSave.setIdentifier(theReal.getIdentifier());
        forSave.setCreatedDate(theReal.getCreatedDate());
        forSave.setRowVersion(theReal.getRowVersion());
        this.verifyPut(theReal, forSave);
        return (T) this.getRepository().save(forSave);
    }

    @Transactional
    public void delete(PK id) {
        T entity = this.getEntity(id);
        this.verifyDelete(entity);
        this.getRepository().delete(entity);
    }

    @Transactional
    public void delete(T entity) {
        this.verifyDelete(entity);
        this.getRepository().delete(entity);
    }

    public T getEntity(PK id) {
        Optional<T> entity = this.getRepository().findById(id);
        if (!entity.isPresent()) {
            throw new ResourceNotFoundException(this.getRepository().getClass().getSimpleName());
        } else {
            return (T) entity.get();
        }
    }

    public List<T> all() {
        return this.getRepository().findAll();
    }

    /** @deprecated */
    @Deprecated
    public Page<T> pageable(String tenantId, Pageable pageable) {
        Page resp;
        if (StringUtils.isNotBlank(tenantId)) {
            Specification<T> specification = (root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList();
                predicates.add(criteriaBuilder.equal(root.get("tenantId"), tenantId));
                return criteriaBuilder.and((Predicate[])predicates.toArray(new Predicate[predicates.size()]));
            };
            resp = this.getRepository().findAll(specification, pageable);
        } else {
            resp = this.getRepository().findAll(pageable);
        }

        return resp;
    }
}
