package ecommerce.storeapi.service;

import ecommerce.storeapi.connection.*;
import ecommerce.storeapi.model.ProductTag;
import ecommerce.storeapi.repository.ProductTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductTagService {

    private final ProductTagRepository productTagRepository;


    public AddProductTagResponse addProductTag (AddProductTagRequest request) {
        productTagRepository.save(request.getProductTag());
        return AddProductTagResponse.builder().productTag(request.getProductTag()).build();
    }

    public GetAllProductTagsResponse getAllProductTags () {
        Iterable<ProductTag> allProductTags = productTagRepository.findAll();
        return GetAllProductTagsResponse.builder().allProductTags(allProductTags).build();
    }

    public DeleteProductTagResponse deleteProductTag (DeleteProductTagRequest request) {
        ProductTag productTagToDelete = productTagRepository.findById(request.getProductTagName()).orElseThrow();
        productTagRepository.delete(productTagToDelete);
        return DeleteProductTagResponse.builder().productTagDeleted(true).build();
    }
}
