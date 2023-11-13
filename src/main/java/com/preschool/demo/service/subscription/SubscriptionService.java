package com.preschool.demo.service.subscription;

import com.preschool.demo.controller.mapper.SubscriptionMapper;
import com.preschool.demo.data.entity.subscription.Subscription;
import com.preschool.demo.data.repository.BaseRepository;
import com.preschool.demo.data.repository.subscription.SubscriptionRepository;
import com.preschool.demo.service.AbstractEntityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class SubscriptionService extends AbstractEntityService<Subscription, String> {

    private final SubscriptionRepository repository;
    private final SubscriptionMapper mapper;

    @Override
    public BaseRepository<Subscription, String> getRepository() {
        return repository;
    }

    @Override
    protected Subscription verifySave(Subscription entity) {
        return super.verifySave(entity);
    }

    @Override
    protected Subscription verifyPut(Subscription theReal, Subscription forSave) {
        return super.verifyPut(theReal, forSave);
    }

    public Optional<Subscription> findById(String id) {
        return repository.findById(id);
    }
/*

    public Optional<Subscription> findByUsername(String companyId) {
        return repository.findByCompanyId(companyId);
    }
*/


}
