package br.edu.iff.ccc.bsi.forumhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class ForumhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumhubApplication.class, args);
	}
	
	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}


}
