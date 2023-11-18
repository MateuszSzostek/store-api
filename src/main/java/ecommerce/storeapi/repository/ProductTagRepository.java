package ecommerce.storeapi.repository;

import ecommerce.storeapi.model.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTagRepository extends JpaRepository<ProductTag, String> {
}