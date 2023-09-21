package edu.pe.leadyourway.webservices.bike.domain.repository;

import edu.pe.leadyourway.webservices.bike.infrastructure.entity.BicycleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BicycleRepository extends JpaRepository<BicycleEntity, Long> {
}