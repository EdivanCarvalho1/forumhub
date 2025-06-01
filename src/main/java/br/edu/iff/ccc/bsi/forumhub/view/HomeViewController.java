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
import br.edu.iff.ccc.bsi.forumhub.model.Role;
import br.edu.iff.ccc.bsi.forumhub.model.Topic;
import br.edu.iff.ccc.bsi.forumhub.service.CategoryService;
import br.edu.iff.ccc.bsi.forumhub.service.CommentService;
import br.edu.iff.ccc.bsi.forumhub.service.PersonService;
import br.edu.iff.ccc.bsi.forumhub.service.PostService;
import br.edu.iff.ccc.bsi.forumhub.service.RoleService;
import br.edu.iff.ccc.bsi.forumhub.service.TopicService;
import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;

@Controller
public class HomeViewController {

	@Autowired
	private PostService postService;

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


}
