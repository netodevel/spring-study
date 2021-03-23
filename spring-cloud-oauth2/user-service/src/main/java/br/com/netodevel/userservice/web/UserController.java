package br.com.netodevel.userservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.netodevel.userservice.domain.ErrorMessage;
import br.com.netodevel.userservice.domain.User;
import br.com.netodevel.userservice.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> show(@PathVariable("userId") Integer userId) {
		User user = userService.findOne(userId);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<ErrorMessage>(new ErrorMessage("Usuário não encontrado"), HttpStatus.OK);
	}

	@PostMapping("/users")
	public ResponseEntity<?> create(@RequestBody User user) {
		if(userService.userAlreadyExits(user.getEmail())) {
			return new ResponseEntity<ErrorMessage>(new ErrorMessage("E-mail já existente"), HttpStatus.CONFLICT);
		}
		
		User userSaved = userService.save(user);
		return new ResponseEntity<User>(userSaved, HttpStatus.CREATED);
	}
	
}
