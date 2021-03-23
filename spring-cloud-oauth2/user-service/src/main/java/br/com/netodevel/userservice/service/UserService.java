package br.com.netodevel.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.netodevel.userservice.domain.User;
import br.com.netodevel.userservice.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		return userRepository.save(user);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findOne(Integer userId) {
		return userRepository.findOne(userId);
	}
	
	public boolean userAlreadyExits(String email) {
		return userRepository.findByEmail(email) != null ? true : false;
	}
}
