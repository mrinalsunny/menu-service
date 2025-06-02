package app.cloudkitchen.menuservice.repository;

import app.cloudkitchen.menuservice.document.MenuReviewDocument;
import app.cloudkitchen.menuservice.dto.ReviewStatistics;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Review Repository
@Repository
public interface MenuReviewRepository extends MongoRepository<MenuReviewDocument, String> {

    List<MenuReviewDocument> findByMenuItemIdOrderByCreatedAtDesc(String menuItemId);

    List<MenuReviewDocument> findByCustomerId(String customerId);

    @Aggregation(pipeline = {
            "{ '$match': { 'menu_item_id': ?0 } }",
            "{ '$group': { '_id': null, 'avgRating': { '$avg': '$rating' }, 'totalReviews': { '$sum': 1 } } }"
    })
    ReviewStatistics getReviewStatistics(String menuItemId);

    Optional<MenuReviewDocument> findByMenuItemIdAndCustomerId(String menuItemId, String customerId);
}