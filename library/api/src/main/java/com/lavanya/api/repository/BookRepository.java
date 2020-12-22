package com.lavanya.api.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lavanya.api.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
	
	Optional<Book> findBookById(Integer id);
	
	@Query(value="select u from Book u where u.title like %:keyword% or u.author like %:keyword%")
	Page<Book> findBooksByKeyword(Pageable pageable, @Param("keyword") String keyword);

}
