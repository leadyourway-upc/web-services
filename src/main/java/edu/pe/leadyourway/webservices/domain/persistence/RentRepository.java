package edu.pe.leadyourway.webservices.domain.persistence;

import edu.pe.leadyourway.webservices.domain.model.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Long, Rent> {
}
