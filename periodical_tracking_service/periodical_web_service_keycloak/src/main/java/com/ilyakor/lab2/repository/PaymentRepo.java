package com.ilyakor.lab2.repository;

import com.ilyakor.lab2.entity.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepo extends CrudRepository<Payment,Long> {
}
