package com.ilyakor.lab2.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name="client")
public class Client {
    @Id
    @GeneratedValue(generator = "bets_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "bets_id_seq", sequenceName = "bets_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="login", nullable = false)
    private String username;
    
}
