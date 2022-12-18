package com.ilyakor.lab2.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter

@NoArgsConstructor
@Entity
@Getter
@Table(name="periodical")
public class Periodical {
    @Id
    @GeneratedValue(generator = "bets_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "bets_id_seq", sequenceName = "bets_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private float price;

    public void copyFields(Periodical toCopy){
        this.name = toCopy.name;
        this.price = toCopy.price;
    }
}
