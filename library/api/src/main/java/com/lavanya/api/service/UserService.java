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
import com.lavanya.api.model.User;
import com.lavanya.api.repository.UserRepository;

/**
 * Service provider for all business functionalities related to User class.
 * @author lavanya
 */
@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * method to retrieve a particular user identified by its username.
	 * @param username of the user of interest to identify in database.
	 * @return User object.
	 */
	public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
	
	/**
	 * This controller-method updates an user and encode its password in database.
     * 
	 */
	public void updateUser() {
		User user = new User();
		user = findUserByUsername("12345ID");
		String email = user.getEmail();
		
        user.setPassword(bCryptPasswordEncoder.encode(email));
        
        user.setRoles("USER");
        user.setEnabled(true);
        userRepository.save(user);
    }	
	
	/**
	 * method to retrieve a particular user identified by its id.
	 * @param id, id of the user of interest to identify in database.
	 * @return Optional User object.
	 */
	public Optional<User> getUserById (Integer id) {
		
		return userRepository.findById(id);
		
	}
	
	/**
	 * method to create a UserDetail object based of an user of interest.
	 * @param username to retrieve from database the user of interest to generate the UserDetails.
	 * @throws UsernameNotFoundException thrown if the user to be retrieved is not found in database.
	 */
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if(user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("utilisateur inexistant");
        }
    }
	
	/**
	 * method to generate list of authorities of a User of interest.
	 * @param role of the user used to be added in the list of GrantedAuthority.
	 * @return list of GrantedAuthority.
	 */
	private List<GrantedAuthority> getUserAuthority(String role) {
      
        List<GrantedAuthority> grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(role));
        return grantedAuthorities;
    }

	/**
	 * method to generate the UserDetails after authentication.
	 * @param authorities as list of GrantedAuthority associated to the user connected.
	 * @param user that is authenticated.
	 * @return UserDetails.
	 */
    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
