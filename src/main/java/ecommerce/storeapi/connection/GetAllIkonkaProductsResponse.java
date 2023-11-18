package ecommerce.storeapi.connection;

import ecommerce.storeapi.model.IkonkaProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllIkonkaProductsResponse {
    private Iterable<IkonkaProduct> allIkonkaProducts;
}
