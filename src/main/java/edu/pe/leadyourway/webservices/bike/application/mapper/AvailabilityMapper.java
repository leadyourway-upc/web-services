package edu.pe.leadyourway.webservices.bike.application.mapper;

import edu.pe.leadyourway.webservices.bike.application.dto.AvailabilityDto;
import edu.pe.leadyourway.webservices.bike.domain.model.Availability;
import edu.pe.leadyourway.webservices.bike.infrastructure.entity.AvailabilityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AvailabilityMapper {
    AvailabilityMapper INSTANCE = Mappers.getMapper(AvailabilityMapper.class);
    Availability availabilityDtoToModel(AvailabilityDto availabilityDto);
    AvailabilityEntity availabilityModelToEntity(Availability availability);

    Availability availabilityEntityToModel(AvailabilityEntity availabilityEntity);

    List<Availability> availabilityEntityListToModelList(List<AvailabilityEntity> availabilityEntities);
}
