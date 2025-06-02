package app.cloudkitchen.menuservice.repository;

import app.cloudkitchen.menuservice.document.MenuItemDocument;
import app.cloudkitchen.menuservice.dto.MenuByCategory;
import app.cloudkitchen.menuservice.dto.MenuStatistics;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemRepository extends MongoRepository<MenuItemDocument, String>,
        MenuItemRepositoryCustom {

    // Basic queries
    List<MenuItemDocument> findByRestaurantId(String restaurantId);

    List<MenuItemDocument> findByRestaurantIdAndIsAvailable(String restaurantId, Boolean isAvailable);

    List<MenuItemDocument> findByCategoryId(String categoryId);

    List<MenuItemDocument> findByCategoryIdAndIsAvailable(String categoryId, Boolean isAvailable);

    Optional<MenuItemDocument> findByIdAndRestaurantId(String id, String restaurantId);

    // Price range queries
    List<MenuItemDocument> findByRestaurantIdAndPriceBetweenAndIsAvailable(
            String restaurantId, BigDecimal minPrice, BigDecimal maxPrice, Boolean isAvailable);

    // Search queries
    @Query("{ 'restaurant_id': ?0, '$text': { '$search': ?1 }, 'is_available': true }")
    List<MenuItemDocument> findByRestaurantIdAndTextSearch(String restaurantId, String searchText);

    // Aggregation queries
    @Aggregation(pipeline = {
            "{ '$match': { 'restaurant_id': ?0, 'is_available': true } }",
            "{ '$group': { '_id': '$category_id', 'items': { '$push': '$$ROOT' }, 'count': { '$sum': 1 } } }",
            "{ '$lookup': { 'from': 'categories', 'localField': '_id', 'foreignField': '_id', 'as': 'category' } }"
    })
    List<MenuByCategory> findMenuGroupedByCategory(String restaurantId);

    // Popular items
    List<MenuItemDocument> findByRestaurantIdAndIsPopularTrueAndIsAvailableTrue(String restaurantId);

    // Featured items
    List<MenuItemDocument> findByRestaurantIdAndIsFeaturedTrueAndIsAvailableTrue(String restaurantId);

    // Dietary filters
    List<MenuItemDocument> findByRestaurantIdAndDietaryInfoInAndIsAvailable(
            String restaurantId, List<String> dietaryInfo, Boolean isAvailable);

    // Inventory queries
    List<MenuItemDocument> findByRestaurantIdAndTrackInventoryTrueAndStockQuantityLessThanEqual(
            String restaurantId, Integer threshold);

    // Batch operations
    @Query("{ 'restaurant_id': ?0, '_id': { '$in': ?1 } }")
    List<MenuItemDocument> findByRestaurantIdAndIdIn(String restaurantId, List<String> ids);

    // Statistics
    @Aggregation(pipeline = {
            "{ '$match': { 'restaurant_id': ?0 } }",
            "{ '$group': { '_id': null, 'totalItems': { '$sum': 1 }, 'availableItems': { '$sum': { '$cond': ['$is_available', 1, 0] } }, 'avgPrice': { '$avg': '$price' } } }"
    })
    MenuStatistics getMenuStatistics(String restaurantId);
}