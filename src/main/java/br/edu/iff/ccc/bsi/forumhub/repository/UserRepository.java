package br.edu.iff.ccc.bsi.forumhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.bsi.forumhub.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
