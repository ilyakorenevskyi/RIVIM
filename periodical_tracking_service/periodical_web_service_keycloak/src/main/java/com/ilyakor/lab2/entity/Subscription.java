package com.ilyakor.lab2.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="subscription",schema = "public")
public class Subscription {
    @Id
    @GeneratedValue(generator = "bets_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "bets_id_seq", sequenceName = "bets_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "client_id", referencedColumnName="id", nullable = false)
    private Client client;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "periodical_id", referencedColumnName="id", nullable = false)
    private Periodical periodical;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "period")
    private int period;

    @Column(name="status")
    private boolean status;

}
