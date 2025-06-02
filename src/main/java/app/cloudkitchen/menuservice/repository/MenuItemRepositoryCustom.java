package app.cloudkitchen.menuservice.repository;

import app.cloudkitchen.menuservice.document.MenuItemDocument;
import app.cloudkitchen.menuservice.dto.MenuSearchCriteria;
import app.cloudkitchen.menuservice.dto.PopularMenuItem;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

// Custom repository interface
//@Repository
public interface MenuItemRepositoryCustom {
    List<MenuItemDocument> findWithFilters(MenuSearchCriteria criteria);
    List<MenuItemDocument> findNearbyMenuItems(double longitude, double latitude, double radiusKm);
    List<MenuItemDocument> findByAvailabilitySchedule(String restaurantId, LocalDateTime dateTime);
    void updateBulkAvailability(List<String> itemIds, Boolean availability);
    List<PopularMenuItem> findPopularItemsWithStats(String restaurantId, int limit);
}