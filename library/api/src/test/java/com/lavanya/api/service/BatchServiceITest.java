package com.lavanya.api.service;

import com.lavanya.api.dto.BookDto;
import com.lavanya.api.dto.PreBookingDto;
import com.lavanya.api.dto.UserDto;
import com.lavanya.api.mapper.PreBookingMapper;
import com.lavanya.api.mapper.PreBookingMapperImpl;
import com.lavanya.api.model.*;
import com.lavanya.api.repository.BatchRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.access.method.P;

import javax.print.attribute.standard.DocumentName;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BatchServiceITest {

    @InjectMocks
    BatchService batchService;

    @Mock
    BatchRepository batchRepository;

    @Mock
    PreBookingService preBookingService;

    @Mock
    NotifiedService notifiedService;

    @Mock
    PreBookingMapper mapper;

    @Mock
    Notification notification;

    @Mock
    Lending lending;

    @Mock
    Book book;

    @Mock
    Notified notified;

    @Mock
    BookDto bookDto;

    @Mock
    UserDto userDto;

    @Before
    public void setUp() {
        mapper = new PreBookingMapperImpl();
        userDto = new UserDto();
        userDto.setId(1);
        userDto.setEmail("userEmail@gmail.com");
        userDto.setFirstName("Jean");
        userDto.setLastName("Minet");

        bookDto = new BookDto();
        bookDto.setId(2);
        bookDto.setTitle("Star Wars");
        bookDto.setAuthor("Collectif");
        bookDto.setRemainingStock(1);
        bookDto.setTotalPreBooking(1);


    }

    @Test
    public void getListOfUserNotInTimeTest() {
        List<Lending> lendingList = new ArrayList<>();
        lendingList.add(new Lending(1, LocalDate.of(2021, 04,21), LocalDate.now().plusDays(2),false, new User(null,"userEmail@gmail.com",null,"Lorie","Michet",false,null,null), new Book(null, "Lily la Souris", "Marie Leland", null, null, null)));
        lendingList.add(new Lending(2, LocalDate.of(2021, 03,11), LocalDate.now().plusDays(4),false, new User(null,"userEmail2@gmail.com",null,"Marc","Strong",false,null,null), new Book(null, "Ratatouille", "Walt Disney", null, null, null)));

        when(batchRepository.getLendingsNotInTime(LocalDate.now())).thenReturn(lendingList);

        List<Notification> notifications = batchService.getListOfUserNotInTime();
        String actualFullname = notifications.get(0).getFullId();
        String actualEmail = notifications.get(1).getEmail();

        Assert.assertEquals(2,notifications.size());
        Assert.assertEquals("Lorie Michet", actualFullname);
        Assert.assertEquals("userEmail2@gmail.com", actualEmail);

    }

//    Check that no list of users to warn is generated when none of the books booked is available
    @Test
    public void getListOfUsersWithBooksReadyToLendWithNoSuccessTest() {

        List<Book> bookList = new ArrayList<>();

        bookList.add(new Book(null, null, null, 0, 10,1));
        bookList.add(new Book(null, null, null, 0, 10,1));

        List<Notification> notificationList = batchService.getListOfUsersWithBooksReadyToLend();
        Assert.assertTrue(notificationList.isEmpty());
    }

    //    Check that no list of users to warn is generated when none of the books booked is available
    @Test
    public void getListOfUsersWithBooksReadyToLendSuccessTest() {

        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, null, null, 0, 10,1));
        bookList.add(new Book(2, "Star Wars", "Collectif", 1, 10,1));

        List<PreBookingDto> preBookingDtoList = new ArrayList<>();

        preBookingDtoList.add(new PreBookingDto(1, LocalDateTime.now(),userDto, bookDto));

        when(preBookingService.getListOfDistinctBooksPreBooked()).thenReturn(bookList);
        when(preBookingService.getListOfPreBookingByBookId(2)).thenReturn(preBookingDtoList);
        when(notifiedService.getNotifiedByPreBookingId(anyInt())).thenReturn(Optional.empty());

        List<Notification> notificationList = batchService.getListOfUsersWithBooksReadyToLend();

        String actualFullname = notificationList.get(0).getFullId();

        Assert.assertEquals(1,notificationList.size());
        Assert.assertEquals("Jean Minet", actualFullname);
    }

    //    Check that a user already warned less than 48h ago do not generate any list for notifications
    @Test
    public void getListOfUsersWithBooksReadyToLendWithLess48HNoticeTest() {

        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, null, null, 0, 10,1));
        bookList.add(new Book(2, "Star Wars", "Collectif", 1, 10,1));

        List<PreBookingDto> preBookingDtoList = new ArrayList<>();

        preBookingDtoList.add(new PreBookingDto(1, LocalDateTime.now(),userDto, bookDto));
        preBookingDtoList.add(new PreBookingDto(2, LocalDateTime.now(),userDto, bookDto));

        notified = new Notified(1,1,LocalDate.now().minusDays(1));

        when(preBookingService.getListOfDistinctBooksPreBooked()).thenReturn(bookList);
        when(preBookingService.getListOfPreBookingByBookId(2)).thenReturn(preBookingDtoList);
        when(notifiedService.getNotifiedByPreBookingId(anyInt())).thenReturn(Optional.of(notified));

        List<Notification> notificationList = batchService.getListOfUsersWithBooksReadyToLend();

        Assert.assertTrue(notificationList.isEmpty());
    }

    //    Check that a user already warned will have is pre-booking deleted if he hasn't get his booking 48h later.
    @Test
    public void getListOfUsersWithBooksReadyToLendWithMoreThan48HNoticeTest() {

        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, null, null, 0, 10,1));
        bookList.add(new Book(2, "Star Wars", "Collectif", 1, 10,1));

        List<PreBookingDto> preBookingDtoList = new ArrayList<>();

        preBookingDtoList.add(new PreBookingDto(1, LocalDateTime.now(),userDto, bookDto));
        preBookingDtoList.add(new PreBookingDto(2, LocalDateTime.now(),userDto, bookDto));

        notified = new Notified(1,1,LocalDate.now().minusDays(3));

        when(preBookingService.getListOfDistinctBooksPreBooked()).thenReturn(bookList);
        when(preBookingService.getListOfPreBookingByBookId(2)).thenReturn(preBookingDtoList);
        when(notifiedService.getNotifiedByPreBookingId(anyInt())).thenReturn(Optional.of(notified));

        List<Notification> notificationList = batchService.getListOfUsersWithBooksReadyToLend();

        verify(preBookingService).deletePreBooking(anyInt());
    }

    //    Check that a user already warned will more than 48h ago will have his pre-booking deleted and the next user in waiting list will be warned.
    @Test
    public void getListOfUsersWithBooksReadyToLendWarnNextUserTest() {

        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, null, null, 0, 10,1));
        bookList.add(new Book(2, "Star Wars", "Collectif", 1, 10,1));

        List<PreBookingDto> preBookingDtoList = new ArrayList<>();
        List<PreBookingDto> preBookingDtoList1 = new ArrayList<>();

        preBookingDtoList.add(new PreBookingDto(1, LocalDateTime.now(),userDto, bookDto));
        preBookingDtoList.add(new PreBookingDto(2, LocalDateTime.now(),new UserDto(2,"Julie", "Nour", "jNour@gmail.com") , bookDto));

        preBookingDtoList1.add(new PreBookingDto(2, LocalDateTime.now(),new UserDto(2,"Julie", "Nour", "jNour@gmail.com") , bookDto));

        notified = new Notified(1,1,LocalDate.now().minusDays(3));

        when(preBookingService.getListOfDistinctBooksPreBooked()).thenReturn(bookList);
        when(preBookingService.getListOfPreBookingByBookId(2)).thenReturn(preBookingDtoList,preBookingDtoList1);
        when(notifiedService.getNotifiedByPreBookingId(anyInt())).thenReturn(Optional.of(notified),Optional.empty());

        List<Notification> notificationList = batchService.getListOfUsersWithBooksReadyToLend();
        String actualUserEmailToNotify = notificationList.get(0).getEmail();

        verify(preBookingService).deletePreBooking(anyInt());
        Assert.assertEquals(1,notificationList.size());
        Assert.assertEquals("jNour@gmail.com",actualUserEmailToNotify);

    }
}
