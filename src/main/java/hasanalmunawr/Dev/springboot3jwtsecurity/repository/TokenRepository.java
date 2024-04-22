package hasanalmunawr.Dev.springboot3jwtsecurity.repository;

import hasanalmunawr.Dev.springboot3jwtsecurity.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {


    @Query(value = """
            select t from Token t inner join UserEntity u\s
            on t.user.id = u.id\s
            where u.id = :id and (t.isExpired = false or t.isRevoked = false)\s
            """)
    List<Token> findAllValidTokensByUser(Integer id);

    Optional<Token> findByToken(String token);
}
