package com.ilyakor.lab2.service;

import com.ilyakor.lab2.dto.SubscriptionRequest;
import com.ilyakor.lab2.entity.Client;
import com.ilyakor.lab2.entity.Periodical;
import com.ilyakor.lab2.entity.Subscription;
import com.ilyakor.lab2.repository.ClientRepo;
import com.ilyakor.lab2.repository.PeriodicalRepo;
import com.ilyakor.lab2.repository.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepo subsRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private PeriodicalRepo periodicalRepo;

    public List<Subscription> getAllSubs() {

        return (List<Subscription>) subsRepo.findAll();

    }

    public List<Subscription> getAllSubsByClient(Client client) {

        return (List<Subscription>) subsRepo.findSubscriptionsByClient(client);

    }

    public Subscription addSub(Client client, SubscriptionRequest sub) {

        Subscription subscription = new Subscription();



        Optional<Periodical> periodical = periodicalRepo.findById(sub.getPeriodicalId());

        if (periodical.isPresent()) {
            subscription.setClient(client);
            subscription.setPeriodical(periodical.get());
            subscription.setStatus(true);
            subscription.setPeriod(sub.getPeriod());
            subscription.setStartDate(java.sql.Date.valueOf(LocalDate.now()));
            return subsRepo.save(subscription);
        }

        return null;
    }

}
