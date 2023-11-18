package ecommerce.storeapi.connection;

import ecommerce.storeapi.model.IkonkaProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetIkonkaProductResponse {
    private Optional<IkonkaProduct> ikonkaProduct;
}
