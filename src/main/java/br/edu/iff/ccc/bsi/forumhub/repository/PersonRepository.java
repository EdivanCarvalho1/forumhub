package br.edu.iff.ccc.bsi.forumhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.bsi.forumhub.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	
	public List<Person> findByNicknameAndPhone(String nickname, String phone);
	
	public Person findByNickname(String nickname);
}
