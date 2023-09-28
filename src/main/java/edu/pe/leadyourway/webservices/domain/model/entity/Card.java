package edu.pe.leadyourway.webservices.domain.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="card_number", nullable = false, length = 16)
    private String cardNumber;

    @Column(name="card_type", nullable = false, length = 10)
    private String cardType;

    @Column(name="card_cvv", nullable = false)
    private String cardCvv;

    @Column(name="card_expiration_date", nullable = false)
    private LocalDate cardExpirationDate;

    @Column(name = "card_amount", nullable = false)
    private Double cardAmount;

    @Column(name = "card_holder", nullable = false)
    private String cardHolder;

    @Column(name = "card_main", nullable = false)
    private boolean cardMain;
}