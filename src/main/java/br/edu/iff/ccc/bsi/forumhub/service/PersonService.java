package br.edu.iff.ccc.bsi.forumhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.forumhub.model.Person;
import br.edu.iff.ccc.bsi.forumhub.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepository;
	
	public Optional<List<Person>> findAll(){
		
		return Optional.ofNullable(personRepository.findAll());
		
	}
	
	public Optional<Person> findOne(Long id){
		
		Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuários não existentes"));
		
		return Optional.ofNullable(person);
		
	}
	
	public void postUser(Person person) {
		
		personRepository.save(person);
		
	}
	
	public void deleteUser(Long id) {
		
		personRepository.deleteById(id);
	}
	
	public void updateUser(Long id) {
		
		Person updatedPerson = findAll()
				.orElseThrow(() -> new RuntimeException("Usuários não existentes"))
				.stream()
				.filter(person -> person.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		
		personRepository.save(updatedPerson);
	}
	
	public Optional<Person> findByNickname(String nickname) {
		Person person = personRepository.findByNickname(nickname).orElseThrow(() -> new RuntimeException("Pessoa não encontrada!"));
		
		return Optional.ofNullable(person);
	}
	
	public Optional<List<Person>> findByNicknameAndPhone(String nickname, String phone) {
		List<Person> personList = personRepository.findByNicknameAndPhone(nickname, phone).orElseThrow(() -> new RuntimeException("Pessoa não encontrada!"));
		
		return Optional.ofNullable(personList);
	}
}
