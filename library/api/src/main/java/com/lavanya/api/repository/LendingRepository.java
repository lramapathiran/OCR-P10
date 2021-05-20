package com.lavanya.api.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.lavanya.api.model.Lending;
import com.lavanya.api.model.Book;

import javax.persistence.TypedQuery;

/**
 * Repository extending JPA repository for persistence of Lending object.
 * @author lavanya
 */
@Repository
public interface LendingRepository extends JpaRepository<Lending, Integer> {


	/**
	 * Query to retrieve the list of lending of a specific user ordered by due date.
	 * @param userId for id of the user of interest.
	 * @return List of lending.
	 */
	List<Lending> findAllByUserIdOrderByDueDate(int userId);
	
	/**
	 * Query to retrieve a lending of interest.
	 * @param id for id of the lending of interest.
	 * @return Optional lending.
	 */
	Optional<Lending> findById(Integer id);

	/**
	 * Query to retrieve a lending for a particular book and made by the user connected.
	 * @param userId for id of the user of interest.
	 * @param bookId for id of the book of interest.
	 * @return a lending Object.
	 */
    Optional<Lending> findByUserIdAndBookId(int userId, int bookId);

	/**
	 * Query to retrieve the earliest due date of lendings made for a particular book.
	 * @param book to establish which due dates of wich lendings we are looking for.
	 * @return a lending Object.
	 */
	@Query(value="select min(u.dueDate) from Lending u where u.book = ?1")
    LocalDate getEarliestDueDateByBookId(Book book);
}
