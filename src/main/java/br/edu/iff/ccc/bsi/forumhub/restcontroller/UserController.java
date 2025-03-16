package br.edu.iff.ccc.bsi.forumhub.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.bsi.forumhub.model.User;
import br.edu.iff.ccc.bsi.forumhub.service.UserService;

@RestController()
@RequestMapping(path="/api/v1")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path="/user")
	public Optional<List<User>> getAllUsers(){
		
		Optional<List<User>> userList = userService.getAllUsers();
		return userList;
	}
	
}
