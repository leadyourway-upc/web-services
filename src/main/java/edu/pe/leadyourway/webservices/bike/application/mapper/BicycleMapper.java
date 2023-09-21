package edu.pe.leadyourway.webservices.bike.application.mapper;

import edu.pe.leadyourway.webservices.bike.domain.model.Bicycle;
import edu.pe.leadyourway.webservices.bike.infrastructure.entity.BicycleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BicycleMapper {
    BicycleMapper INSTANCE = Mappers.getMapper(BicycleMapper.class);
    BicycleEntity bicycleModelToEntity(Bicycle bicycle);

    Bicycle bicycleEntityToModel(BicycleEntity bicycleEntity);

    List<Bicycle> bicycleEntityListToModelList(List<BicycleEntity> bicycleEntities);
}
