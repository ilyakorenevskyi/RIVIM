package com.ilyakor.lab2.repository;

import com.ilyakor.lab2.entity.Periodical;
import org.springframework.data.repository.CrudRepository;

public interface PeriodicalRepo extends CrudRepository<Periodical, Long> {
}
