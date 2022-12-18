package com.ilyakor.lab2.controller;

import com.ilyakor.lab2.dto.PeriodicalRequest;
import com.ilyakor.lab2.entity.Periodical;
import com.ilyakor.lab2.repository.PeriodicalRepo;
import com.ilyakor.lab2.service.PeriodicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/periodicals")
public class PeriodicalController {

    @Autowired
    private PeriodicalService service;

    @RolesAllowed({"admin","user"})
    @GetMapping
    public ResponseEntity<List<Periodical>> getAllPeriodicals(@RequestHeader String Authorization){
        return ResponseEntity.ok(service.getAllPeriodicals());
    }

    @RolesAllowed({"admin"})
    @PostMapping
    public ResponseEntity<Periodical> addPeriodical(@RequestHeader String Authorization,@RequestBody PeriodicalRequest periodical){
        System.out.println(periodical.getName());
        return ResponseEntity.ok(service.addPeriodical(periodical));
    }

    @RolesAllowed({"admin","user"})
    @GetMapping("/{id}")
    public ResponseEntity<Periodical> getPeriodical(@RequestHeader String Authorization, @PathVariable long id){
        Periodical periodical =  service.getPeriodical(id);
        if(periodical==null){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(periodical);
    }

    @RolesAllowed({"admin"})
    @PutMapping("/{id}")
    public ResponseEntity<Periodical> updatePeriodical(@RequestHeader String Authorization,@PathVariable long id, @RequestBody PeriodicalRequest periodical){
        Periodical updatedPeriodical =  service.updatePeriodical(id,periodical);
        if(updatedPeriodical==null){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(updatedPeriodical);
    }

    @RolesAllowed({"admin"})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePeriodical(@RequestHeader String Authorization,@PathVariable long id){
        service.deletePeriodical(id);
        return ResponseEntity.ok().build();
    }

}
