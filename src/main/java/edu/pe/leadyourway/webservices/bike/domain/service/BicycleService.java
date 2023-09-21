package edu.pe.leadyourway.webservices.bike.domain.service;

import edu.pe.leadyourway.webservices.bike.domain.model.Bicycle;

import java.time.LocalDate;
import java.util.List;

public interface BicycleService {
    public abstract Bicycle createBicycle(Bicycle bicycle);
    public abstract Bicycle getBicycleById(Long bicycle_id);
    public abstract Bicycle updateBicycle(Long bicycleId, Bicycle bicycle);
    public abstract void deleteBicycle(Long bicycle_id);
    public abstract List<Bicycle> getAllBicycles();
    public abstract List<Bicycle> getAllAvailableBicycles(LocalDate start_date, LocalDate end_date);
}
