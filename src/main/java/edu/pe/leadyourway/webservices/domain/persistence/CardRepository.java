package edu.pe.leadyourway.webservices.domain.persistence;

import edu.pe.leadyourway.webservices.domain.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Long, Card> {
}
