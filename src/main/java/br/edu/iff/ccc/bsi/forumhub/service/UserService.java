package br.edu.iff.ccc.bsi.forumhub.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.forumhub.model.User;
import br.edu.iff.ccc.bsi.forumhub.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Optional<User> findUserById(Long id){
		if(id == null) {
			throw new IllegalArgumentException("Id não pode ser nulo");
		}
		Optional<User> user = userRepository.findById(id);
		
		return user;		
	}
	
	public Optional<List<User>> getAllUsers() {
	    List<User> usersList = userRepository.findAll();
	    return Optional.ofNullable(usersList.isEmpty() ? null : usersList);
	}
	
}
