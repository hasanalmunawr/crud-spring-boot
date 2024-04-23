package hasanalmunawr.Dev.springboot3jwtsecurity.repository;

import hasanalmunawr.Dev.springboot3jwtsecurity.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddresRepository extends JpaRepository<AddressEntity, Integer> {
}
