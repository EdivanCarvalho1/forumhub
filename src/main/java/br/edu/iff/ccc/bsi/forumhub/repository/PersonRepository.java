package br.edu.iff.ccc.bsi.forumhub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.bsi.forumhub.enums.STATUS;
import br.edu.iff.ccc.bsi.forumhub.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	
	public Optional<List<Person>> findByNicknameAndPhone(String nickname, String phone);
	
	public Optional<Person> findByNickname(String nickname);
	

    @Query("SELECT p FROM Person p WHERE p.status = :status ORDER BY p.points DESC")
    List<Person> findActivePersonsOrderedByPoints(@Param("status") STATUS status);
}
