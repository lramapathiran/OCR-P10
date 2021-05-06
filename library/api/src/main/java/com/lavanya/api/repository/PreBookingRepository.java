package com.lavanya.api.repository;

import com.lavanya.api.model.PreBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository extending JPA repository for persistence of PreBooking object.
 * @author lavanya
 */
@Repository
public interface PreBookingRepository extends JpaRepository<PreBooking, Integer> {

    /**
     * Query to retrieve the list of pre-bookings of a specific user ordered by timestamp.
     * @param userId for id of the user of interest.
     * @return List of pre-bookings.
     */
    List<PreBooking> findAllByUserIdOrderByTimeStamp(int userId);
}
