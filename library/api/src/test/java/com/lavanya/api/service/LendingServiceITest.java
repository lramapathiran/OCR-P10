package com.lavanya.api.service;

import com.lavanya.api.error.ExtendDueDateFailed;
import com.lavanya.api.error.SaveLendingFailed;
import com.lavanya.api.model.Lending;
import com.lavanya.api.model.User;
import com.lavanya.api.repository.LendingRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LendingServiceITest {

    @InjectMocks
    LendingService lendingService;

    @Mock
    LendingRepository lendingRepository;

    @Mock
    Lending lending;

    @Mock
    User user;

    @Before
    public void init(){
        lending = new Lending();
    }

    @Test(expected = ExtendDueDateFailed.class)
    public void getBookDueDateExtendedWithThrownExceptionTest() {
        lending.setId(1);
        lending.setBook(null);
        lending.setLendingDate(LocalDate.of(2021, 04,21));
        lending.setDueDate(LocalDate.of(2021,05,21));
        lending.setIsExtended(false);
        lending.setUser(null);
        int id = 1;
        Optional<Lending> optional = Optional.of(lending);

        when(lendingRepository.findById(id)).thenReturn(optional);

        lendingService.getBookDueDateExtended(id);
    }

    @Test
    public void getBookDueDateExtendedWithSuccessTest() {
        lending.setId(1);
        lending.setBook(null);
        lending.setLendingDate(LocalDate.of(2021, 04,21));
        lending.setDueDate(LocalDate.now().plusDays(2));
        lending.setIsExtended(false);
        lending.setUser(null);
        int id = 1;
        Optional<Lending> optional = Optional.of(lending);

        LocalDate initialDueDate = lending.getDueDate();
        LocalDate expectedExtentedDate = initialDueDate.plusWeeks(4);

        when(lendingRepository.findById(id)).thenReturn(optional);
        when(lendingRepository.save(Mockito.any(Lending.class))).thenAnswer(i -> i.getArguments()[0]);

        lendingService.getBookDueDateExtended(id);

        Assert.assertEquals(expectedExtentedDate, lending.getDueDate());

    }

    @Test(expected = SaveLendingFailed.class)
    public void saveFailedTest(){

        lending.setBook(null);
        lending.setId(null);
        user = new User("bb","bb@gmail.com","pppppppppp","georges","bbb",false,null,null);
        when(lendingRepository.save(Mockito.any(Lending.class))).thenAnswer(i -> i.getArguments()[0]);
        lendingService.save(lending,user);

    }
}

