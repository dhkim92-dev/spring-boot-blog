package kr.dohoonkim.blog.repositories.user;

import kr.dohoonkim.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query(value="select u from User u where u.email=:email")
  public Optional<User> findByEmail(String email);
}

