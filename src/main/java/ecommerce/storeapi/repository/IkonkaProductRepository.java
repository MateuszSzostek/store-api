package ecommerce.storeapi.repository;

import ecommerce.storeapi.model.IkonkaProduct;
import ecommerce.storeapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IkonkaProductRepository extends JpaRepository<IkonkaProduct, String> {
}