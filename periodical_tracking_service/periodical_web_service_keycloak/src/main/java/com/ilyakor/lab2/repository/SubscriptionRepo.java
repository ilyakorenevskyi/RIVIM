package com.ilyakor.lab2.repository;

import com.ilyakor.lab2.entity.Client;
import com.ilyakor.lab2.entity.Subscription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionRepo extends CrudRepository<Subscription,Long> {
    public List<Subscription> findSubscriptionsByClient(Client client);
}
