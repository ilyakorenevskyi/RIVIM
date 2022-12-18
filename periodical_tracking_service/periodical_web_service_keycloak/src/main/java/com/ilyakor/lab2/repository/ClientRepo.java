package com.ilyakor.lab2.repository;

import com.ilyakor.lab2.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepo extends CrudRepository<Client, Long> {
    public Client findClientByUsername(String username);
}
