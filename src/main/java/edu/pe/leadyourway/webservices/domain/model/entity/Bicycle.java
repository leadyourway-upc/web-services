package edu.pe.leadyourway.webservices.domain.model.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="bicycles")
public class Bicycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="bicycle_name", nullable = false, length = 50)
    private String bicycleName;

    @Column(name="bicycle_description", nullable = false, length = 200)
    private String bicycleDescription;

    @Column(name="bicycle_price", nullable = false)
    private double bicyclePrice;

    @Column(name="bicycle_size", nullable = false, length = 10)
    private String bicycleSize;

    @Column(name="bicycle_model", length = 50)
    private String bicycleModel;

    @Column(name="image_data", nullable = true)
    private String imageData;

}
