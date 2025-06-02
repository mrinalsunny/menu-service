package app.cloudkitchen.menuservice.service;

import app.cloudkitchen.menuservice.document.RestaurantDocument;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    List<RestaurantDocument> findAllActive();
    Optional<RestaurantDocument> findById(String id);
    RestaurantDocument save(RestaurantDocument restaurant);
    void deleteById(String id);
}