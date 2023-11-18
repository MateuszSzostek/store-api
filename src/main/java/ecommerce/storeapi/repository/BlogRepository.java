package ecommerce.storeapi.repository;

import ecommerce.storeapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Post, String> {
}