package ecommerce.storeapi.connection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkProductRequest {
    private String firstProductId;
    private String secondProductId;
}
