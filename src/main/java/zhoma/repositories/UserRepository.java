package zhoma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhoma.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
