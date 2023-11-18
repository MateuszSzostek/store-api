package ecommerce.storeapi.service;

import ecommerce.storeapi.connection.*;
import ecommerce.storeapi.model.Category;
import ecommerce.storeapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public AddCategoryResponse addCategory(AddCategoryRequest request) {
        Category newCategory = Category.builder().name(request.getCategoryName()).build();
        categoryRepository.save(newCategory);
        return AddCategoryResponse.builder().category(newCategory).build();
    }

    public DeleteCategoryResponse deleteCategory(DeleteCategoryRequest request) {
        Category categoryToDelete = categoryRepository.findById(request.getCategoryName()).orElseThrow();
        categoryRepository.delete(categoryToDelete);
        return DeleteCategoryResponse.builder().categoryDeleted(true).build();
    }

    public GetAllCategoriesResponse getAllCategories() {
        Iterable<Category> allCategories = categoryRepository.findAll();
        return GetAllCategoriesResponse.builder().allCategories(allCategories).build();
    }

    public LinkCategoriesResponse linkCategories(LinkCategoriesRequest request) {
        Category childCategory = categoryRepository.findById(request.getChildCategoryName()).orElseThrow();
        Category parentCategory = categoryRepository.findById(request.getParentCategoryName()).orElseThrow();
        childCategory.setParentName(parentCategory.getParentName());
        return LinkCategoriesResponse.builder().category(childCategory).build();
    }

    public UnlinkCategoriesResponse unlinkCategories(UnlinkCategoriesRequest request) {
        Category category = categoryRepository.findById(request.getCategory().getName()).orElseThrow();
        category.setParentName(null);
        return UnlinkCategoriesResponse.builder().category(category).build();
    }
}
