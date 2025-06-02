package app.cloudkitchen.menuservice.service;

import app.cloudkitchen.menuservice.document.MenuItemDocument;
import app.cloudkitchen.menuservice.dto.MenuSearchCriteria;
import app.cloudkitchen.menuservice.dto.MenuStatistics;
import app.cloudkitchen.menuservice.dto.PopularMenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuItemService {
    List<MenuItemDocument> findWithFilters(MenuSearchCriteria criteria);
    Optional<MenuItemDocument> findById(String id);
    MenuItemDocument save(MenuItemDocument item);
    void deleteById(String id);
    MenuStatistics getMenuStatistics(String restaurantId);
    List<PopularMenuItem> findPopularItemsWithStats(String restaurantId, int limit);
}