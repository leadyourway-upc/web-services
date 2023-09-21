package edu.pe.leadyourway.webservices.bike.application.service;

import edu.pe.leadyourway.webservices.bike.application.dto.AvailabilityDto;
import edu.pe.leadyourway.webservices.bike.application.exception.ResourceNotFoundException;
import edu.pe.leadyourway.webservices.bike.application.exception.ValidationException;
import edu.pe.leadyourway.webservices.bike.application.mapper.AvailabilityMapper;
import edu.pe.leadyourway.webservices.bike.domain.model.Availability;
import edu.pe.leadyourway.webservices.bike.domain.model.Bicycle;
import edu.pe.leadyourway.webservices.bike.domain.repository.AvailabilityRepository;
import edu.pe.leadyourway.webservices.bike.domain.service.AvailabilityService;
import edu.pe.leadyourway.webservices.bike.domain.service.BicycleService;
import edu.pe.leadyourway.webservices.bike.infrastructure.entity.AvailabilityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {
    private final AvailabilityRepository availabilityRepository;
    private final BicycleService bicycleService;

    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository, BicycleService bicycleService) {
        this.availabilityRepository = availabilityRepository;
        this.bicycleService = bicycleService;
    }

    @Override
    public Availability create(AvailabilityDto availabilityDto) {
        validateAvailability(availabilityDto);
        existsBicycle(availabilityDto.getBicycleId());
        Availability availability = AvailabilityMapper.INSTANCE.availabilityDtoToModel(availabilityDto);
        AvailabilityEntity availabilityEntity = availabilityRepository.save(AvailabilityMapper.INSTANCE.availabilityModelToEntity(availability));
        return AvailabilityMapper.INSTANCE.availabilityEntityToModel(availabilityEntity);
    }

    @Override
    public Availability getById(Long availability_id) {
        existsAvailability(availability_id);
        AvailabilityEntity availabilityEntity = availabilityRepository.findById(availability_id).orElse(null);
        return AvailabilityMapper.INSTANCE.availabilityEntityToModel(availabilityEntity);
    }

    @Override
    public void delete(Long availability_id) {
        existsAvailability(availability_id);
        availabilityRepository.deleteById(availability_id);
    }

    @Override
    public boolean existsBetweenDates(Long bicycle_id, LocalDate availability_start_date, LocalDate availability_end_date) {
        existsBicycle(bicycle_id);
        return availabilityRepository.existsByBicycleIdAndAvailabilityStartDateLessThanEqualAndAvailabilityEndDateGreaterThanEqual(bicycle_id, availability_start_date, availability_end_date);
    }

    @Override
    public List<Availability> getByBicycleId(Long bicycle_id) {
        existsBicycle(bicycle_id);
        List<AvailabilityEntity> availabilityEntities = availabilityRepository.findByBicycleId(bicycle_id);
        return AvailabilityMapper.INSTANCE.availabilityEntityListToModelList(availabilityEntities);
    }

    @Override
    public List<Availability> getByBicycleIdAndAvailabilityType(Long bicycle_id, boolean availability_type) {
        existsBicycle(bicycle_id);
        List<AvailabilityEntity> availabilityEntities = availabilityRepository.findByBicycleIdAndAvailabilityType(bicycle_id, availability_type);
        return AvailabilityMapper.INSTANCE.availabilityEntityListToModelList(availabilityEntities);
    }

    private void existsAvailability (Long availability_id) {
        if (!availabilityRepository.existsById(availability_id))
            throw new ResourceNotFoundException("Availability with id " + availability_id + " does not exist");
    }

    private void existsBicycle(Long bicycle_id) {
        Optional<Bicycle> bicycle = Optional.of(bicycleService.getBicycleById(bicycle_id));
        if (bicycle.isEmpty())
            throw new ResourceNotFoundException("Bicycle with id " + bicycle_id + " does not exist");
    }


    private void validateAvailability(AvailabilityDto availability) {
        if (availability.getAvailabilityStartDate().isAfter(availability.getAvailabilityEndDate()))
            throw new ValidationException("Availability start date must be before availability end date");
        if (availability.getAvailabilityStartDate().isBefore(LocalDate.now()))
            throw new ValidationException("Availability start date must be after today");
        if (availability.getBicycleId() == null)
            throw new ValidationException("Bicycle id must not be null");
    }
}
