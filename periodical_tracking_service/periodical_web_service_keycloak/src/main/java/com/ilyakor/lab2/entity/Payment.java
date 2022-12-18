package com.ilyakor.lab2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name= "payment", schema = "public")
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(generator = "bets_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "bets_id_seq", sequenceName = "bets_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @Column(name="date")
    private Date date;

    @Column(name="amount")
    private float totalCost;

}
