package app.cloudkitchen.menuservice.controller;

import app.cloudkitchen.menuservice.document.MenuItemDocument;
import app.cloudkitchen.menuservice.dto.MenuSearchCriteria;
import app.cloudkitchen.menuservice.dto.MenuStatistics;
import app.cloudkitchen.menuservice.dto.PopularMenuItem;
import app.cloudkitchen.menuservice.exception.MenuItemNotFoundException;
import app.cloudkitchen.menuservice.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menu-items")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @PostMapping("/search")
    public List<MenuItemDocument> search(@RequestBody MenuSearchCriteria criteria) {
        return menuItemService.findWithFilters(criteria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemDocument> getById(@PathVariable String id) {
        return ResponseEntity.ok(
                menuItemService.findById(id)
                        .orElseThrow(() -> new MenuItemNotFoundException("Menu item not found with ID: " + id))
        );
    }

    @PostMapping
    public MenuItemDocument create(@RequestBody MenuItemDocument item) {
        return menuItemService.save(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemDocument> update(@PathVariable String id, @RequestBody MenuItemDocument item) {
        if (!id.equals(item.getId())) {
            throw new IllegalArgumentException("ID in path does not match ID in the request body.");
        }
        return ResponseEntity.ok(menuItemService.save(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        menuItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/statistics/{restaurantId}")
    public MenuStatistics getStatistics(@PathVariable String restaurantId) {
        return menuItemService.getMenuStatistics(restaurantId);
    }

    @GetMapping("/popular/{restaurantId}")
    public List<PopularMenuItem> getPopular(@PathVariable String restaurantId,
                                            @RequestParam(defaultValue = "5") int limit) {
        return menuItemService.findPopularItemsWithStats(restaurantId, limit);
    }
}