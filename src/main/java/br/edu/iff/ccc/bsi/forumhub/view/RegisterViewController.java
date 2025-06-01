package br.edu.iff.ccc.bsi.forumhub.view;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.iff.ccc.bsi.forumhub.model.Person;
import br.edu.iff.ccc.bsi.forumhub.service.PersonService;
import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;

@Controller
public class RegisterViewController {
	
	@Autowired
	PersonService personService;
	
	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("register");
		mv.addObject("person", new Person());
		mv.addObject("title", "Register");
		return mv;
	}

	@PostMapping("/person")
	public ModelAndView register(@Valid @ModelAttribute("person") Person person, BindingResult result, Model model) {
		ModelAndView mv = new ModelAndView("register");
		Person p = new Person(null, person.getNickname(), person.getEmail(), person.getPassword(), person.getPhone(),
				LocalDateTime.now(), "ACTIVE", 0);
		personService.postPerson(p);
		mv.addObject("message", "Usuário criado com sucesso!");
		return mv;
	}
}
