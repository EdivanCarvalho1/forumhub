package br.edu.iff.ccc.bsi.forumhub.view;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.iff.ccc.bsi.forumhub.exception.NotFoundException;
import br.edu.iff.ccc.bsi.forumhub.model.Category;
import br.edu.iff.ccc.bsi.forumhub.model.Person;
import br.edu.iff.ccc.bsi.forumhub.model.Post;
import br.edu.iff.ccc.bsi.forumhub.model.PostDTO;
import br.edu.iff.ccc.bsi.forumhub.model.Role;
import br.edu.iff.ccc.bsi.forumhub.model.Topic;
import br.edu.iff.ccc.bsi.forumhub.service.CategoryService;
import br.edu.iff.ccc.bsi.forumhub.service.PersonService;
import br.edu.iff.ccc.bsi.forumhub.service.PostService;
import br.edu.iff.ccc.bsi.forumhub.service.RoleService;
import br.edu.iff.ccc.bsi.forumhub.service.TopicService;
import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;

@Controller
public class ViewController {

	@Autowired
	private PostService postService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PersonService personService;
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home");

		List<Post> posts = postService.findAll().orElseThrow(() -> new NotFoundException("No posts found"));

		List<Post> mostRecentPosts = posts.stream()
				.sorted((p1, p2) -> p2.getCreationDate().compareTo(p1.getCreationDate())).limit(5).toList();

		mv.addObject("posts", posts);
		mv.addObject("recents", mostRecentPosts);

		return mv;
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("title", "Login");
		return mv;
	}

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

	@GetMapping("/teste")
	public ModelAndView teste() {
		ModelAndView mv = new ModelAndView("form-test");
		return mv;
	}

	@PostMapping("/teste")
	public ModelAndView submitForm(@Valid @ModelAttribute("role") Role role, BindingResult result, Model model) {
		ModelAndView mv = new ModelAndView("form-test");

		if (result.hasErrors()) {
			mv.addObject("message", "Validation errors occurred!");
			return mv;
		}

		roleService.postRole(role);
		mv.addObject("message", "Role created successfully!");

		return mv;
	}

	@GetMapping("/postar")
	public ModelAndView postar() {
		ModelAndView mv = new ModelAndView("form-post");
		mv.addObject("post", new PostDTO());
		return mv;
	}

	@PostMapping("/postar")
	public ModelAndView submitPost(@Valid @ModelAttribute("post") PostDTO post, BindingResult result, Model model) {
		ModelAndView mv = new ModelAndView("form-post");
		Category c = new Category(null, "Geral", LocalDateTime.now(), "Categoria Geral");
		Topic p = new Topic(null, post.getTitle(), LocalDateTime.now(), c);
		Person person = new Person(null, "John Doe", "johndoe@email.com", "123456", "999999999",
				LocalDateTime.of(2021, 10, 1, 15, 0, 0, 0), "ACTIVE", 0);
		Post pt = new Post(null, 0, 0, post.getContent(), LocalDateTime.now(), person, p);
		
		categoryService.postCategory(c);
		topicService.postTopic(p);
		postService.postPost(pt);
		
		return mv;
	}

}
