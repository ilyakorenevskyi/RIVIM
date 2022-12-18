package com.ilyakor.lab2.service;

import com.ilyakor.lab2.dto.PeriodicalRequest;
import com.ilyakor.lab2.entity.Periodical;
import com.ilyakor.lab2.repository.PeriodicalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodicalService {

    @Autowired
    private PeriodicalRepo repo;


    public List<Periodical> getAllPeriodicals(){
        List<Periodical> periodicals;
        periodicals = (List<Periodical>) repo.findAll();
        return periodicals;
    }

    public Periodical getPeriodical(long id){
        return repo.findById(id).orElse(null);
    }


    public Periodical addPeriodical(PeriodicalRequest periodical){
        Periodical newPeriodical = new Periodical();
        newPeriodical.setName(periodical.getName());
        newPeriodical.setPrice(periodical.getPrice());
        repo.save(newPeriodical);
        return newPeriodical;
    }

    public void deletePeriodical(long id){
        if(!repo.existsById(id)){
            throw new RuntimeException("Periodical doesn't exist");
        }
        else {
            repo.deleteById(id);
        }
    }

    public Periodical updatePeriodical(long id, PeriodicalRequest periodical){
        Periodical updated = repo.findById(id).orElse(null);
        if(updated == null) return null;
        else{
            updated.setName(periodical.getName());
            updated.setPrice(periodical.getPrice());
            repo.save(updated);
        }
        return updated;
    }

}
