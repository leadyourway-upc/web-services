package edu.pe.leadyourway.webservices.bike.application.service;

import edu.pe.leadyourway.webservices.bike.application.exception.ResourceNotFoundException;
import edu.pe.leadyourway.webservices.bike.application.mapper.BicycleMapper;
import edu.pe.leadyourway.webservices.bike.domain.model.Availability;
import edu.pe.leadyourway.webservices.bike.domain.model.Bicycle;
import edu.pe.leadyourway.webservices.bike.domain.repository.BicycleRepository;
import edu.pe.leadyourway.webservices.bike.domain.service.AvailabilityService;
import edu.pe.leadyourway.webservices.bike.domain.service.BicycleService;
import edu.pe.leadyourway.webservices.bike.infrastructure.entity.BicycleEntity;
import jakarta.xml.bind.ValidationException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BicycleServiceImpl implements BicycleService {
    private final BicycleRepository bicycleRepository;
    private final AvailabilityService availabilityService;

    public BicycleServiceImpl(BicycleRepository bicycleRepository, AvailabilityService availabilityRepository) {
        this.bicycleRepository = bicycleRepository;
        this.availabilityService = availabilityRepository;
    }

    @Override
    public Bicycle createBicycle(Bicycle bicycle) {
        validateBicycle(bicycle);
        BicycleEntity bicycleEntity = BicycleMapper.INSTANCE.bicycleModelToEntity(bicycle);
        return BicycleMapper.INSTANCE.bicycleEntityToModel(bicycleRepository.save(bicycleEntity));
    }

    @Override
    public Bicycle getBicycleById(Long bicycle_id) {
        existsBicycleByBicycleId(bicycle_id);
        BicycleEntity bicycleEntity = bicycleRepository.findById(bicycle_id).orElse(null);
        return BicycleMapper.INSTANCE.bicycleEntityToModel(bicycleEntity);
    }

    @Override
    public Bicycle updateBicycle(Long bicycleId, Bicycle bicycle) {
        existsBicycleByBicycleId(bicycleId);
        bicycle.setId(bicycleId);
        BicycleEntity bicycleEntity = BicycleMapper.INSTANCE.bicycleModelToEntity(bicycle);
        bicycleRepository.save(bicycleEntity);
        return bicycle;
    }

    @Override
    public void deleteBicycle(Long bicycle_id) {
        existsBicycleByBicycleId(bicycle_id);
        bicycleRepository.deleteById(bicycle_id);
    }

    @Override
    public List<Bicycle> getAllBicycles() {
        List<BicycleEntity> bicycleEntities = bicycleRepository.findAll();
        return BicycleMapper.INSTANCE.bicycleEntityListToModelList(bicycleEntities);
    }

    @Override
    public List<Bicycle> getAllAvailableBicycles(LocalDate start_date, LocalDate end_date) {
        List<Bicycle> bicycles = new ArrayList<>();
        for (Bicycle bicycle: BicycleMapper.INSTANCE.bicycleEntityListToModelList(bicycleRepository.findAll())) {
            System.out.println("bicycle id: " + bicycle.getId());
            List<Availability> availabilities = availabilityService.getByBicycleId(bicycle.getId());
            boolean isAvailable = true;
            for (Availability availability: availabilities) {
                if (availability.getAvailabilityStartDate().equals(start_date) || availability.getAvailabilityEndDate().equals(start_date) ||
                        availability.getAvailabilityStartDate().equals(end_date) || availability.getAvailabilityEndDate().equals(end_date)) {
                    isAvailable = false;
                    break;
                }

                if (availability.getAvailabilityStartDate().isAfter(end_date) || availability.getAvailabilityEndDate().isBefore(start_date))
                    continue;

                if (availability.getAvailabilityStartDate().isBefore(start_date) && availability.getAvailabilityEndDate().isAfter(end_date)){
                    isAvailable = false;
                    break;
                }

                if (availability.getAvailabilityStartDate().isBefore(start_date) && availability.getAvailabilityEndDate().isBefore(end_date)){
                    isAvailable = false;
                    break;
                }

                if (availability.getAvailabilityStartDate().isAfter(start_date) && availability.getAvailabilityEndDate().isAfter(end_date)){
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable)
                bicycles.add(bicycle);
        }
        return bicycles;
    }

    private void existsBicycleByBicycleId(Long bicycleId) {
        if (!bicycleRepository.existsById(bicycleId)) {
            throw new ResourceNotFoundException("No existe la bicicleta con el id: " + bicycleId);
        }
    }
    @SneakyThrows
    private void validateBicycle(Bicycle bicycle) {
        if (bicycle.getBicycleName() == null || bicycle.getBicycleName().isEmpty()) {
            throw new ValidationException("El nombre de la bicicleta debe ser obligatorio");
        }
        if (bicycle.getBicycleName().length() > 50) {
            throw new ValidationException("El nombre de la bicicleta no debe exceder los 50 caracteres");
        }
        if (bicycle.getBicycleDescription() == null || bicycle.getBicycleDescription().isEmpty()) {
            throw new ValidationException("La descripción de la bicicleta debe ser obligatoria");
        }
        if (bicycle.getBicycleDescription().length() > 200) {
            throw new ValidationException("La descripción de la bicicleta no debe exceder los 200 caracteres");
        }
        if (bicycle.getBicyclePrice() == 0) {
            throw new ValidationException("El precio de la bicicleta debe ser obligatorio");
        }
        if (bicycle.getBicyclePrice() < 0) {
            throw new ValidationException("El precio de la bicicleta no debe ser negativo");
        }
        if (bicycle.getBicycleSize() == null || bicycle.getBicycleSize().isEmpty()) {
            throw new ValidationException("El tamaño de la bicicleta debe ser obligatorio");
        }
    }
}
