package br.edu.iff.ccc.bsi.forumhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.forumhub.model.Post;
import br.edu.iff.ccc.bsi.forumhub.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	PostRepository postRepository;
	
	public Optional<List<Post>> findAll(){
		
		return Optional.ofNullable(postRepository.findAll());
		
	}
	
	public Optional<Post> findOne(Long id){
		
		Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("O comentário não existe"));
		
		return Optional.ofNullable(post);
		
	}
	
	public void postPost(Post post) {
		
		postRepository.save(post);
		
	}
	
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}
	
	public void updatePost(Long id) {
		
		Post updatedPost = findAll()
				.orElseThrow(() -> new RuntimeException("Usuários não existentes"))
				.stream()
				.filter(post -> post.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		
		postRepository.save(updatedPost);
	}
}
