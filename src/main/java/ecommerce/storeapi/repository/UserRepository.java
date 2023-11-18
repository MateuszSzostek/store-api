package ecommerce.storeapi.repository;

import java.util.Optional;

import ecommerce.storeapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
