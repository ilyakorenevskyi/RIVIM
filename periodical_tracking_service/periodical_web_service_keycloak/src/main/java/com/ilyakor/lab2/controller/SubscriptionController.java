package com.ilyakor.lab2.controller;

import com.ilyakor.lab2.dto.SubscriptionRequest;
import com.ilyakor.lab2.entity.Client;
import com.ilyakor.lab2.entity.Subscription;
import com.ilyakor.lab2.repository.SubscriptionRepo;
import com.ilyakor.lab2.service.ClientService;
import com.ilyakor.lab2.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subsService;

    @Autowired
    private ClientService clientService;

    @RolesAllowed({"admin","user"})
    @GetMapping
    public ResponseEntity<List<Subscription>> getSubs(@RequestHeader String Authorization){
        Client client = clientService.getUser(Authorization);
        if(client == null){
            return ResponseEntity.badRequest().build();
        }
        List<Subscription> subs = subsService.getAllSubsByClient(client);
        return ResponseEntity.ok(subs);
    }

    @RolesAllowed("user")
    @PostMapping
    public ResponseEntity<Subscription> addSub(@RequestHeader String Authorization, @RequestBody SubscriptionRequest sub){
        Client client = clientService.getUser(Authorization);
        if(client == null){
            return ResponseEntity.badRequest().build();
        }
        Subscription newSub = subsService.addSub(client, sub);
        return ResponseEntity.ok(newSub);
    }


}
