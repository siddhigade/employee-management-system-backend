package EMSApp.EMS.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import EMSApp.EMS.entities.User;



public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
