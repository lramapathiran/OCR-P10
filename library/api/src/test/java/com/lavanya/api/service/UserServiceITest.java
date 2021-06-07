package com.lavanya.api.service;

import com.lavanya.api.model.User;
import com.lavanya.api.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceITest {

    @InjectMocks
    UserService userService;

    @Mock
    User user;

    @Mock
    UserRepository userRepository;


    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameFailTest() {
        String username = "Julien";

        when(userRepository.findByUsername(username)).thenReturn(null);

        userService.loadUserByUsername(username);

    }

}
