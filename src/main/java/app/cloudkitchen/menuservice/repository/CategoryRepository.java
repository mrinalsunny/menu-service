package app.cloudkitchen.menuservice.repository;

import app.cloudkitchen.menuservice.document.CategoryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Category Repository
@Repository
public interface CategoryRepository extends MongoRepository<CategoryDocument, String> {

    List<CategoryDocument> findByRestaurantIdAndIsActiveTrue(String restaurantId);

    List<CategoryDocument> findByRestaurantIdOrderByDisplayOrderAsc(String restaurantId);

    Optional<CategoryDocument> findByRestaurantIdAndName(String restaurantId, String name);

    @Query("{ 'restaurant_id': ?0, 'is_active': true }")
    List<CategoryDocument> findActiveCategoriesByRestaurant(String restaurantId);
}