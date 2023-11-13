package com.preschool.demo.data.repository.subscription;

import com.preschool.demo.data.entity.subscription.Subscription;
import com.preschool.demo.data.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends BaseRepository<Subscription, String> {
}
