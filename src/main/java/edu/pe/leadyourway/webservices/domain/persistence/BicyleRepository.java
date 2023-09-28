package edu.pe.leadyourway.webservices.domain.persistence;

import edu.pe.leadyourway.webservices.domain.model.entity.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BicyleRepository extends JpaRepository<Long, Bicycle> {
}
