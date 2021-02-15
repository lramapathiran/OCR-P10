package com.lavanya.api.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.lavanya.api.model.Book;

/**
 * Repository extending JPA repository for persistence of Book object.
 * @author lavanya
 */
@Repository
public interface BookRepository extends PagingAndSortingRepository<Book,Integer> {
	
	/**
	 * Query to retrieve a book of interest.
	 * @param id for id of the book of interest.
	 * @return Optional Book.
	 */
	Optional<Book> findBookById(Integer id);
	
	/**
	 * Query to retrieve a list of books using pagination based or not with the following filter criteria:
	 * keyword that can be book title or book author.
	 * @param pageable for pagination
	 * @param keyword to specify the element to filter the book search with.
	 * @return Page of Book.
	 */
	@Query("select u from Book u where ?1 is null or concat(u.title, u.author) LIKE %?1%")
	Page<Book> findFilteredBook(String keyword, Pageable pageable);

}
