package com.lavanya.api.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.lavanya.api.model.Book;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book,Integer> {
	
	Optional<Book> findBookById(Integer id);
	
	@Query("select u from Book u where ?1 is null or concat(u.title, u.author) LIKE %?1%")
	Page<Book> findFilteredBook(String keyword, Pageable pageable);

}
