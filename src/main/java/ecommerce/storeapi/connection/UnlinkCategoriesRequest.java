package ecommerce.storeapi.connection;

import ecommerce.storeapi.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnlinkCategoriesRequest {
    private Category category;
}