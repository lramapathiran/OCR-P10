package com.lavanya.api.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lavanya.api.configs.JwtTokenProvider;
import com.lavanya.api.model.User;
import com.lavanya.api.repository.UserRepository;
import com.lavanya.api.service.UserService;


/**
 * Rest Controller to control all the requests related to User object.
 * @author lavanya
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository users;
    
    /**
     *  POST requests for /saveUser endpoint.
     * This controller-method updates an user and encode its password in database.
     * 
     */	
    @PostMapping("/saveUser")
    public void saveUser() {
    	userService.updateUser();
    }
	
    /**
     *  POST requests for /login endpoint.
     * This controller-method send data required for user authentication.
     * 
     * @param data is the bean where the password and username of the user are stored to authenticate the user.
     * @return ResponseEntity to confirm the authentication was successful with the authenticated user username and a token.
     */	
	@SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthBody data) {
        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username, this.users.findByUsername(username).getRoles());
            Map<Object, Object> authInfo = new HashMap<>();
            authInfo.put("username", username);
            authInfo.put("token", token);
            return ok(authInfo);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("L'identifiant et/ou le mot de passe sont invalides!");
        }
    }
	
}
