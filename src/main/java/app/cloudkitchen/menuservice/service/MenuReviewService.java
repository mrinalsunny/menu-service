package app.cloudkitchen.menuservice.service;

import app.cloudkitchen.menuservice.document.MenuReviewDocument;
import app.cloudkitchen.menuservice.dto.ReviewStatistics;

import java.util.List;
import java.util.Optional;

public interface MenuReviewService {
    List<MenuReviewDocument> findByMenuItemId(String menuItemId);
    ReviewStatistics getReviewStatistics(String menuItemId);
    MenuReviewDocument save(MenuReviewDocument review);
    Optional<MenuReviewDocument> findByMenuItemIdAndCustomerId(String menuItemId, String customerId);
}