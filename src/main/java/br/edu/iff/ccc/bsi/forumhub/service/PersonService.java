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

	public Optional<List<Person>> findAll() {

		return Optional.ofNullable(personRepository.findAll());

	}

	public Optional<Person> findOne(Long id) {

		Person person = personRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuários não existentes"));

		return Optional.ofNullable(person);

	}

	public void postPerson(Person person) {

		personRepository.save(person);

	}

	public void deletePerson(Long id) {

		personRepository.deleteById(id);
	}

	public void updatePerson(Long id, Person updatedPerson) {
		
        Person existingPerson = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada!"));
        
        existingPerson.setNickname(updatedPerson.getNickname());
        existingPerson.setPassword(updatedPerson.getPassword());
        existingPerson.setPhone(updatedPerson.getPhone());
        existingPerson.setPoints(updatedPerson.getPoints());
        existingPerson.setEmail(updatedPerson.getEmail());
        existingPerson.setSignInDate(updatedPerson.getSignInDate());
        existingPerson.setStatus(updatedPerson.getStatus());
        
        personRepository.save(existingPerson);
	}

	public Optional<Person> findByNickname(String nickname) {
		Person person = personRepository.findByNickname(nickname)
				.orElseThrow(() -> new RuntimeException("Pessoa não encontrada!"));

		return Optional.ofNullable(person);
	}

	public Optional<List<Person>> findByNicknameAndPhone(String nickname, String phone) {
		List<Person> personList = personRepository.findByNicknameAndPhone(nickname, phone)
				.orElseThrow(() -> new RuntimeException("Pessoa não encontrada!"));

		return Optional.ofNullable(personList);
	}
}
