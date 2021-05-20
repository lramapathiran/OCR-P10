package com.lavanya.api.repository;

import com.lavanya.api.model.Book;
import com.lavanya.api.model.PreBooking;
import com.lavanya.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    List<PreBooking> findAllByUserIdOrderByTime(int userId);

    /**
     * Query to retrieve the list of all pre-bookings made for a particuler book ordered by timestamp.
     * @param bookId id of the book of interest
     * @return List of pre-bookings.
     */
    List<PreBooking> findALlByBookIdOrderByTime(int bookId);

    /**
     * Query to retrieve the total amount made for a particular book.
     * @param book for which we need to determine the amount of copies already pre-booked.
     * @return Integer for the resulting count.
     */
    @Query(value = "select count(*) from PreBooking u where u.book = ?1")
    Integer numberOfPreBookingByBookId(Book book);

}

