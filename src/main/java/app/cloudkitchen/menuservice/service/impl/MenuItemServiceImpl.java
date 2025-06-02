package app.cloudkitchen.menuservice.service.impl;

import app.cloudkitchen.menuservice.document.MenuItemDocument;
import app.cloudkitchen.menuservice.dto.MenuSearchCriteria;
import app.cloudkitchen.menuservice.dto.MenuStatistics;
import app.cloudkitchen.menuservice.dto.PopularMenuItem;
import app.cloudkitchen.menuservice.repository.MenuItemRepository;
import app.cloudkitchen.menuservice.repository.MenuItemRepositoryCustom;
import app.cloudkitchen.menuservice.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;

    @Override
    public List<MenuItemDocument> findWithFilters(MenuSearchCriteria criteria) {
        return menuItemRepository.findWithFilters(criteria);
    }

    @Override
    public Optional<MenuItemDocument> findById(String id) {
        return menuItemRepository.findById(id);
    }

    @Override
    public MenuItemDocument save(MenuItemDocument item) {
        return menuItemRepository.save(item);
    }

    @Override
    public void deleteById(String id) {
        menuItemRepository.deleteById(id);
    }

    @Override
    public MenuStatistics getMenuStatistics(String restaurantId) {
        return menuItemRepository.getMenuStatistics(restaurantId);
    }

    @Override
    public List<PopularMenuItem> findPopularItemsWithStats(String restaurantId, int limit) {
        return menuItemRepository.findPopularItemsWithStats(restaurantId, limit);
    }
}