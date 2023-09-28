package edu.pe.leadyourway.webservices.domain.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="rents")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="rent_start_date", nullable = false)
    private LocalDate rentStartDate;

    @Column(name="rent_end_date", nullable = false)
    private LocalDate rentEndDate;

    @Column(name="rent_price", nullable = false)
    private Double rentPrice;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "bicycle_id")
    private Bicycle bicycle;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "card_id")
    private Card card;
}
