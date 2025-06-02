package app.cloudkitchen.menuservice.service;

import app.cloudkitchen.menuservice.document.CategoryDocument;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDocument> findByRestaurantId(String restaurantId);
    Optional<CategoryDocument> findById(String id);
    CategoryDocument save(CategoryDocument category);
    void deleteById(String id);
}