package hasanalmunawr.Dev.springboot3jwtsecurity.repository;

import hasanalmunawr.Dev.springboot3jwtsecurity.entity.StudentEntity;
import hasanalmunawr.Dev.springboot3jwtsecurity.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentReporitory extends JpaRepository<StudentEntity, String> {
}
