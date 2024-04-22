package hasanalmunawr.Dev.springboot3jwtsecurity.repository;

import hasanalmunawr.Dev.springboot3jwtsecurity.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String username);
}
