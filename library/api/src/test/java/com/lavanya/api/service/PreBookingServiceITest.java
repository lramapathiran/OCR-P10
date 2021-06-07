package com.lavanya.api.service;


import com.lavanya.api.dto.BookDto;
import com.lavanya.api.dto.PreBookingDto;
import com.lavanya.api.dto.UserDto;
import com.lavanya.api.error.SaveBookingFailed;
import com.lavanya.api.mapper.PreBookingMapper;
import com.lavanya.api.mapper.PreBookingMapperImpl;
import com.lavanya.api.model.Book;
import com.lavanya.api.model.Lending;
import com.lavanya.api.model.PreBooking;
import com.lavanya.api.model.User;
import com.lavanya.api.repository.BookRepository;
import com.lavanya.api.repository.PreBookingRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PreBookingServiceITest {

    @InjectMocks
    PreBookingService preBookingService;

    @Mock
    PreBookingRepository preBookingRepository;

    @Mock
    LendingService lendingService;

    @Mock
    PreBooking preBooking;

    @Mock
    PreBookingDto preBookingDto;

    @Mock
    PreBookingMapper mapper;

    @Mock
    Book book;

    @Mock
    BookDto bookDto;

    @Mock
    User user;

    @Mock
    UserDto userDto;

    @Mock
    Lending lending;

    @Mock
    BookRepository bookRepository;

    @Before
    public void setUp() {
        mapper = new PreBookingMapperImpl();
        preBooking = new PreBooking();
        preBookingDto = new PreBookingDto();

        book = new Book();
        book.setId(2);
        book.setAuthor("Albert Camus");
        book.setTitle("L'étranger");
        book.setRemainingStock(3);
        book.setFullStock(10);
        book.setTotalPreBooking(0);

        bookDto = new BookDto();
        bookDto.setId(2);
        bookDto.setAuthor("Emmanuelle Massonaud");
        bookDto.setTitle("Sami et Julie, L'anniversaire de Julie");
        bookDto.setRemainingStock(2);
        bookDto.setFullStock(4);
        bookDto.setTotalPreBooking(0);

        user = new User();
        user.setId(1);

        userDto = new UserDto();
        userDto.setId(1);
    }

//    On teste ici que la méthode mapper permet bien la conversion de preBooking vers son dto
    @Test
    public void getListOfPreBookingByUserIdToDtoTest(){

        List<PreBooking> preBookingList = new ArrayList<>();
        preBookingList.add(new PreBooking(1,LocalDateTime.now(),user, new Book(1, "L'étranger","Albert Camus",3,10,0)));
        preBookingList.add(new PreBooking(2,LocalDateTime.now(),user, new Book(2, "Sami et Julie, L'anniversaire de Julie","Emmanuelle Massonaud",2,4,0)));


        when(preBookingRepository.findAllByUserIdOrderByTime(1)).thenReturn(preBookingList);
        List<PreBookingDto> preBookingDtoList = preBookingService.getListOfPreBookingByUserId(user.getId());
        String actualTitle = preBookingDtoList.get(0).getBookDto().getTitle();
        String expectedTitle = "L'étranger";

        assertFalse(preBookingDtoList.isEmpty());
        Assert.assertEquals(expectedTitle,actualTitle);
    }

    //    On teste ici que la réservation n'est pas enregistré quand l'utilisateur tente de réserver un
//    ouvrage qui est déjà en cours d'emprunt par celui-ci!
    @Test(expected = SaveBookingFailed.class)
            public void saveFailedTest() {

        preBookingDto.setBookDto(bookDto);
        preBookingDto.setId(1);
        preBookingDto.setTime(LocalDateTime.now());
        preBookingDto.setUserDto(userDto);

        lending = new Lending(1,LocalDate.of(2021, 04,21), LocalDate.now().plusDays(2),false, user, book);
        Optional<Lending> optional = Optional.of(lending);

        when(lendingService.getLendingByUserIdAndBookId(user.getId(),book.getId())).thenReturn(optional);

        preBooking = preBookingService.save(preBookingDto,user);
    }

    //    On teste ici que la réservation s'enregistre bien quand l'utilisateur n'a pas d'emprunt en cours sur l'ouvrage à réserver!
    @Test
    public void saveSucceedTest() {

        preBookingDto.setBookDto(bookDto);
        preBookingDto.setId(1);
        preBookingDto.setTime(LocalDateTime.now());
        preBookingDto.setUserDto(userDto);

        Optional<Lending> optional = Optional.empty();

        when(lendingService.getLendingByUserIdAndBookId(user.getId(),book.getId())).thenReturn(Optional.empty());
        when(preBookingRepository.save(Mockito.any(PreBooking.class))).thenAnswer(i -> i.getArguments()[0]);
        when(bookRepository.save(Mockito.any(Book.class))).thenAnswer(i -> i.getArguments()[0]);

        preBooking = preBookingService.save(preBookingDto,user);
        int totalPreBookingActual = preBooking.getBook().getTotalPreBooking();
        int totalPreBookingExpected = bookDto.getTotalPreBooking() + 1;

        Assert.assertEquals(totalPreBookingExpected,totalPreBookingActual);
    }

    @Test
    public void getListOfPreBookingByBookIdTest() {
        LocalDateTime expectedTime = LocalDateTime.of(2021,04,21,10,22,19);
        List<PreBooking> preBookingList = new ArrayList<>();
        preBookingList.add(new PreBooking(1,LocalDateTime.now(),user, new Book(2, "Sami et Julie, L'anniversaire de Julie","Emmanuelle Massonaud",2,4,0)));
        preBookingList.add(new PreBooking(2,expectedTime,user, new Book(2, "Sami et Julie, L'anniversaire de Julie","Emmanuelle Massonaud",2,4,0)));

        when(preBookingRepository.findALlByBookIdOrderByTime(2)).thenReturn(preBookingList);
        List<PreBookingDto> preBookingDtoList = preBookingService.getListOfPreBookingByBookId(2);
        int actualId = preBookingDtoList.get(1).getId();
        LocalDateTime actualTime = preBookingDtoList.get(1).getTime();

        assertFalse(preBookingDtoList.isEmpty());
        Assert.assertEquals(2,preBookingDtoList.size());
        Assert.assertEquals(2,actualId);
        Assert.assertEquals(expectedTime,actualTime);
    }

    @Test
    public void deletePreBookingTestFail() {
        when(preBookingRepository.findById(2)).thenReturn(Optional.empty());
        preBookingService.deletePreBooking(2);
        verify(preBookingRepository, never()).delete(any(PreBooking.class));
    }

    @Test
    public void deletePreBookingTestSuccced() {

        preBooking = new PreBooking(2,LocalDateTime.now(),user, new Book(2, "Sami et Julie, L'anniversaire de Julie","Emmanuelle Massonaud",2,4,0));
        when(preBookingRepository.findById(2)).thenReturn(Optional.of(preBooking));
        preBookingService.deletePreBooking(2);
        verify(preBookingRepository).delete(preBooking);
    }
}
