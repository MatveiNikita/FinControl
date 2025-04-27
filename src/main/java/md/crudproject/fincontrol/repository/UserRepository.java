package md.crudproject.fincontrol.repository;

import md.crudproject.fincontrol.model.User;
import md.crudproject.fincontrol.service.impl.UserServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> getUsersByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByUserId(UUID id);
}
