package edu.pe.leadyourway.webservices.bike.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bicycle {
    private Long id;

    private String bicycleName;

    private String bicycleDescription;

    private double bicyclePrice;

    private String bicycleSize;

    private String bicycleModel;

    private String imageData;
}
