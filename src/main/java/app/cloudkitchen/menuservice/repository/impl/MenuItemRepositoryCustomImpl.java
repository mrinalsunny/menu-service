package app.cloudkitchen.menuservice.repository.impl;

import app.cloudkitchen.menuservice.document.MenuItemDocument;
import app.cloudkitchen.menuservice.dto.MenuSearchCriteria;
import app.cloudkitchen.menuservice.dto.PopularMenuItem;
import app.cloudkitchen.menuservice.dto.SortDirection;
import app.cloudkitchen.menuservice.repository.MenuItemRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

// Custom repository implementation
@Component
public class MenuItemRepositoryCustomImpl implements MenuItemRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<MenuItemDocument> findWithFilters(MenuSearchCriteria criteria) {
        Query query = new Query();

        // Restaurant filter
        if (criteria.getRestaurantId() != null) {
            query.addCriteria(Criteria.where("restaurant_id").is(criteria.getRestaurantId()));
        }

        // Availability filter
        if (criteria.getIsAvailable() != null) {
            query.addCriteria(Criteria.where("is_available").is(criteria.getIsAvailable()));
        }

        // Category filter
        if (criteria.getCategoryIds() != null && !criteria.getCategoryIds().isEmpty()) {
            query.addCriteria(Criteria.where("category_id").in(criteria.getCategoryIds()));
        }

        // Price range filter
        if (criteria.getMinPrice() != null || criteria.getMaxPrice() != null) {
            Criteria priceCriteria = Criteria.where("price");
            if (criteria.getMinPrice() != null) {
                priceCriteria.gte(criteria.getMinPrice());
            }
            if (criteria.getMaxPrice() != null) {
                priceCriteria.lte(criteria.getMaxPrice());
            }
            query.addCriteria(priceCriteria);
        }

        // Dietary filters
        if (criteria.getDietaryInfo() != null && !criteria.getDietaryInfo().isEmpty()) {
            query.addCriteria(Criteria.where("dietary_info").in(criteria.getDietaryInfo()));
        }

        // Allergen exclusion
        if (criteria.getExcludeAllergens() != null && !criteria.getExcludeAllergens().isEmpty()) {
            query.addCriteria(Criteria.where("allergens").nin(criteria.getExcludeAllergens()));
        }

        // Spice level filter
        if (criteria.getMaxSpiceLevel() != null) {
            query.addCriteria(Criteria.where("spice_level").lte(criteria.getMaxSpiceLevel()));
        }

        // Text search
        if (criteria.getSearchText() != null && !criteria.getSearchText().trim().isEmpty()) {
            query.addCriteria(Criteria.where("$text").is(TextCriteria.forDefaultLanguage().matching(criteria.getSearchText())));
//            query.addCriteria(TextCriteria.forDefaultLanguage().matching(criteria.getSearchText()));
        }

        // Sorting
        if (criteria.getSortBy() != null) {
            Sort sort = Sort.by(criteria.getSortDirection() == SortDirection.ASC ?
                    Sort.Direction.ASC : Sort.Direction.DESC, criteria.getSortBy());
            query.with(sort);
        }

        // Pagination
        if (criteria.getPage() != null && criteria.getSize() != null) {
            query.skip(criteria.getPage() * criteria.getSize()).limit(criteria.getSize());
        }

        System.out.println("Executing query: " + query.toString());
        return mongoTemplate.find(query, MenuItemDocument.class);
    }

    @Override
    public List<MenuItemDocument> findNearbyMenuItems(double longitude, double latitude, double radiusKm) {
        // This would require a geospatial index on restaurant location
        // For now, returning empty list - implementation depends on restaurant location setup
        return Collections.emptyList();
    }

    @Override
    public List<MenuItemDocument> findByAvailabilitySchedule(String restaurantId, LocalDateTime dateTime) {
        // Complex query to check availability schedule
        Query query = new Query();
        query.addCriteria(Criteria.where("restaurant_id").is(restaurantId));
        query.addCriteria(Criteria.where("is_available").is(true));

        // Add availability schedule logic here
        // This is a simplified version - full implementation would check day of week and time slots

        return mongoTemplate.find(query, MenuItemDocument.class);
    }

    @Override
    public void updateBulkAvailability(List<String> itemIds, Boolean availability) {
        Query query = new Query(Criteria.where("id").in(itemIds));
        Update update = new Update().set("is_available", availability)
                .set("updated_at", LocalDateTime.now());

        mongoTemplate.updateMulti(query, update, MenuItemDocument.class);
    }

    @Override
    public List<PopularMenuItem> findPopularItemsWithStats(String restaurantId, int limit) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("restaurant_id").is(restaurantId)
                        .and("is_available").is(true)),
                Aggregation.sort(Sort.Direction.DESC, "popularity_score", "order_count"),
                Aggregation.limit(limit),
                Aggregation.project("name", "price", "popularity_score", "order_count", "rating")
        );

        AggregationResults<PopularMenuItem> results = mongoTemplate.aggregate(
                aggregation, "menu_items", PopularMenuItem.class);

        return results.getMappedResults();
    }
}