package com.ilyakor.lab2.service;

import com.ilyakor.lab2.dto.PaymentRequest;
import com.ilyakor.lab2.entity.Client;
import com.ilyakor.lab2.entity.Payment;
import com.ilyakor.lab2.entity.Periodical;
import com.ilyakor.lab2.entity.Subscription;
import com.ilyakor.lab2.repository.PaymentRepo;
import com.ilyakor.lab2.repository.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private SubscriptionRepo subscriptionRepo;

    public Payment createPayment(PaymentRequest paymentRequest){
        Payment payment = new Payment();

        Optional<Subscription> sub = subscriptionRepo.findById(paymentRequest.getSubscriptionId());

        if (sub.isPresent()) {
            payment.setSubscription(sub.get());
            payment.setDate(java.sql.Date.valueOf(LocalDate.now()));
            payment.setTotalCost(paymentRequest.getAmount());
            return paymentRepo.save(payment);
        }

        return null;
    }

}
