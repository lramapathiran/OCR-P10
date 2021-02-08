package com.lavanya.api.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lavanya.api.model.User;
import com.lavanya.api.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder bCryptPasswordEncoder;
	
	public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
	
	public void updateUser() {
		User user = new User();
		user = findUserByUsername("12345ID");
		String email = user.getEmail();
		
        user.setPassword(bCryptPasswordEncoder.encode(email));
        
        user.setRoles("USER");
        user.setEnabled(true);
        userRepository.save(user);
    }	
	public Optional<User> getUserById (Integer id) {
		
		return userRepository.findById(id);
		
	}
	
	public List<User> getUsersList() {
		
		return userRepository.findAll();
	}
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if(user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }
	
	private List<GrantedAuthority> getUserAuthority(String role) {
      
        List<GrantedAuthority> grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(role));
        return grantedAuthorities;
    }

	
    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
