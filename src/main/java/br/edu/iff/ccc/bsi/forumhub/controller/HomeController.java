package br.edu.iff.ccc.bsi.forumhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="home")
public class HomeController {
	
	@GetMapping()
	public String getHome() {
		return "home";
	}
}
