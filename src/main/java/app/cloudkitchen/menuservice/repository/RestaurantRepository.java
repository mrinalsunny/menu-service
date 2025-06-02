package app.cloudkitchen.menuservice.repository;

import app.cloudkitchen.menuservice.document.RestaurantDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Restaurant Repository (if multi-tenant)
@Repository
public interface RestaurantRepository extends MongoRepository<RestaurantDocument, String> {

    List<RestaurantDocument> findByIsActiveTrue();

    Optional<RestaurantDocument> findByName(String name);

    // Geospatial query for nearby restaurants
    @Query("{ 'address.coordinates': { '$near': { '$geometry': { 'type': 'Point', 'coordinates': [?0, ?1] }, '$maxDistance': ?2 } }, 'is_active': true }")
    List<RestaurantDocument> findNearbyRestaurants(double longitude, double latitude, double maxDistanceMeters);
}