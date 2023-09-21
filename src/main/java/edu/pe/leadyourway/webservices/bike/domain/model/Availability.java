package edu.pe.leadyourway.webservices.bike.domain.model;

import edu.pe.leadyourway.webservices.bike.infrastructure.entity.BicycleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Availability {
    private Long id;

    private boolean availabilityType;

    private LocalDate availabilityStartDate;

    private LocalDate availabilityEndDate;

    private BicycleEntity bicycle;
}
