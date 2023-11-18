package ecommerce.storeapi.connection;

import ecommerce.storeapi.model.ProductTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddProductTagResponse {
    private ProductTag productTag;
}
