package com.lavanya.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lavanya.api.model.User;
import com.lavanya.api.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public User saveUser (User user) {
		
//		if (usernameExists(user.getMemberId())) {
//			throw new UserAlreadyExistException(
//		              "Il existe déjà un utilisateur avec l'identifiant: "  
//		              + user.getMemberId());
//		}
		
		return userRepository.save(user);
	}
	
	public Optional<User> getUserById (Integer id) {
		
		return userRepository.findById(id);
		
	}
	
	public List<User> getUsersList() {
		
		return userRepository.findAll();
	}
	
	public void updateUser(User user) {
		userRepository.save(user);
	}
	
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	private boolean usernameExists(String memberId) {
        return userRepository.findByMemberId(memberId).isPresent();
    }
}
