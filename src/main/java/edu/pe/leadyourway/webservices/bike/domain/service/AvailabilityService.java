package edu.pe.leadyourway.webservices.bike.domain.service;

import edu.pe.leadyourway.webservices.bike.application.dto.AvailabilityDto;
import edu.pe.leadyourway.webservices.bike.domain.model.Availability;

import java.time.LocalDate;
import java.util.List;

public interface AvailabilityService {
    public abstract Availability create(AvailabilityDto availabilityDto);
    public abstract Availability getById(Long availability_id);
    public abstract void delete(Long availability_id);

    public abstract boolean existsBetweenDates(Long bicycle_id, LocalDate availability_start_date, LocalDate availability_end_date);
    public abstract List<Availability> getByBicycleId(Long bicycle_id);
    public abstract List<Availability> getByBicycleIdAndAvailabilityType(Long bicycle_id, boolean availability_type);
}
