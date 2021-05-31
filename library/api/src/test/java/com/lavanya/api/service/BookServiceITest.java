package com.lavanya.api.service;

import com.lavanya.api.model.Book;
import com.lavanya.api.repository.BookRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceITest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private Book book;

    @Before
    public void setUp() {
        book = new Book();
    }


    @Test
    public void getBookByIdTest() {

        book.setId(1);
        book.setAuthor("Albert Camus");
        book.setTitle("L'étranger");
        book.setRemainingStock(3);
        book.setFullStock(10);
        book.setTotalPreBooking(0);
        Optional<Book> optional = Optional.of(book);

        int id = 1;

        when(bookRepository.findBookById(id)).thenReturn(optional);

        Optional<Book> optional1 = bookService.getBookById(id);
        optional1.ifPresent(book1 -> {
            String title = book1.getTitle();
            Assert.assertEquals(book.getTitle(),title);
        });
    }

    @Test
    public void getAllBooksFilteredTest(){

        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(2,"L'étranger","Albert Camus",3,10,0));
        bookList.add(new Book(1,"Sami et Julie, L'anniversaire de Julie","Emmanuelle Massonaud",2,4,0));
        String expectedTitle = "L'étranger";

        bookList.add(book);

        Page bookPage = new PageImpl<>(bookList);

        String keyword = "L'";

        when(bookRepository.findFilteredBook(eq(keyword),any(Pageable.class))).thenReturn(bookPage);

        Page<Book> resultedBookPage = bookService.getAllBooksFiltered(1,"L'");

        String actualTitle = resultedBookPage.getContent().get(0).getTitle();

        Assert.assertEquals(expectedTitle,actualTitle);


    }

}
