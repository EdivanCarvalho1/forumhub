package br.edu.iff.ccc.bsi.forumhub.view;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.iff.ccc.bsi.forumhub.exception.NotFoundException;
import br.edu.iff.ccc.bsi.forumhub.model.Category;
import br.edu.iff.ccc.bsi.forumhub.model.Comment;
import br.edu.iff.ccc.bsi.forumhub.model.Person;
import br.edu.iff.ccc.bsi.forumhub.model.Post;
import br.edu.iff.ccc.bsi.forumhub.model.PostDTO;
import br.edu.iff.ccc.bsi.forumhub.model.Topic;
import br.edu.iff.ccc.bsi.forumhub.service.CategoryService;
import br.edu.iff.ccc.bsi.forumhub.service.CommentService;
import br.edu.iff.ccc.bsi.forumhub.service.PersonService;
import br.edu.iff.ccc.bsi.forumhub.service.PostService;
import br.edu.iff.ccc.bsi.forumhub.service.TopicService;
import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;

@Controller
public class PostViewController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	TopicService topicService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	PersonService personService;
	
	@GetMapping("/postar")
	public ModelAndView postar() {
		ModelAndView mv = new ModelAndView("form-post");
		mv.addObject("post", new PostDTO());
		return mv;
	}

	@PostMapping("/postar")
	public ModelAndView submitPost(@Valid @ModelAttribute("post") PostDTO post, BindingResult result, Model model) {
		ModelAndView mv = new ModelAndView("form-post");
		Category c = new Category(1L, "Geral", LocalDateTime.now(), "Categoria Geral");
		Topic p = new Topic(null, post.getTitle(), LocalDateTime.now(), c);
		Person person = new Person(null, "John Doe", "johndoe@email.com", "123456", "999999999",
				LocalDateTime.of(2021, 10, 1, 15, 0, 0, 0), "ACTIVE", 0);
		Post pt = new Post(null, 0, 0, post.getContent(), LocalDateTime.now(), person, p);
		
		categoryService.postCategory(c);
		topicService.postTopic(p);
		postService.postPost(pt);
		
		return mv;
	}
	@GetMapping("/post/{id}")
	public ModelAndView post(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("post");
        Post post = postService.findOne(id).orElseThrow(() -> new NotFoundException("Post not found"));
        List<Comment> comments = commentService.findByPostId(post.getId()).orElseThrow(() -> new NotFoundException("No comments found for this post"));
        mv.addObject("post", post);
        mv.addObject("comments", comments);
        return mv;
	}
	@DeleteMapping("/post/{id}")
	public String deletePost(@PathVariable Long id) {
		Post post = postService.findOne(id)
			.orElseThrow(() -> new NotFoundException("Post not found"));
		postService.deletePost(post.getId());
		return "redirect:/";
	}
	@PostMapping("/comment/{id}")
	public String postComment(@PathVariable Long id, @RequestParam String content) {
		Post post = postService.findOne(id).orElseThrow(() -> new NotFoundException("Post not found"));
		Person person = new Person(null, "John Doe", "johndoe@email.com", "123456", "999999999",
				LocalDateTime.of(2021, 10, 1, 15, 0, 0, 0), "ACTIVE", 0);
		Comment comment = new Comment(
			null, post, 0, 0, content, LocalDateTime.now(), null, person
		);
		
		personService.postPerson(person);
		commentService.postComment(comment);
		return "redirect:/post/" + id;
	}
}
