package com.lavanya.api.repository;

import com.lavanya.api.model.Notified;
import com.lavanya.api.model.PreBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository extending JPA repository for persistence of Notified object.
 * @author lavanya
 */
@Repository
public interface NotifiedRepository extends JpaRepository<Notified, Integer> {

    Optional<Notified> findByPreBookingId(int preBookingId);
}
