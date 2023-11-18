package ecommerce.storeapi.connection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferAllIkonkaProductsToProductsResponse {
    private boolean allIkonkaIkonsTransferedToProducts;
}
