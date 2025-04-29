package br.edu.iff.ccc.bsi.forumhub.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.iff.ccc.bsi.forumhub.exception.NotFoundException;
import br.edu.iff.ccc.bsi.forumhub.model.Post;
import br.edu.iff.ccc.bsi.forumhub.model.Role;
import br.edu.iff.ccc.bsi.forumhub.service.PostService;
import br.edu.iff.ccc.bsi.forumhub.service.RoleService;
import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;

@Controller
public class ViewController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        
        List<Post> posts = postService.findAll().orElseThrow(() -> new NotFoundException("No posts found"));
        
        mv.addObject("posts", posts);
        
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
		mv.addObject("title", "Register");
		return mv;
	}
	@GetMapping("/teste")
	public ModelAndView teste() {
		ModelAndView mv = new ModelAndView("form-test");
		return mv;
	}
	@PostMapping("/role")
	public ModelAndView submitForm(@Valid @ModelAttribute("role") Role role, BindingResult result, Model model) {
	    ModelAndView mv = new ModelAndView("form-test");

	    if (result.hasErrors()) {
	        mv.addObject("message", "Validation errors occurred!");
	        return mv; // Retorna para o formulário sem salvar
	    }

	    roleService.postRole(role);
	    mv.addObject("message", "Role created successfully!");
	    
	    return mv;
	}

	
}
