package com.lavanya.web.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lavanya.web.model.Book;

public interface BookRepository extends JpaRepository<Book,Integer> {
	
	Optional<Book> findBookById(Integer id);
	
	@Query("select distinct u from Book where "
			+ "(:#{keyword} is null or concat(u.title, u.author) like concat('%',:#{keyword},'%'))"
			)	
	public Page<Book> findFilteredBook(Pageable pageable, @Param ("keyword") String keyword);

}
