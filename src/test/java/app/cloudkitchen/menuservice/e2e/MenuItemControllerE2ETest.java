package app.cloudkitchen.menuservice.e2e;

import app.cloudkitchen.menuservice.document.MenuItemDocument;
import app.cloudkitchen.menuservice.dto.MenuSearchCriteria;
import app.cloudkitchen.menuservice.service.MenuItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MenuItemControllerE2ETest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MenuItemService menuItemService;

    @Test
    public void testCreateAndRetrieveMenuItem() {
        MenuItemDocument menuItem = MenuItemDocument.builder()
                .name("Test Item")
                .description("Test Description")
                .price(new BigDecimal("10.99"))
                .categoryId("test-category")
                .restaurantId("test-restaurant")
                .build();

        ResponseEntity<MenuItemDocument> createResponse = restTemplate.postForEntity("/api/menu-items", menuItem, MenuItemDocument.class);
        assertEquals(200, createResponse.getStatusCodeValue());
        assertNotNull(createResponse.getBody());
        String itemId = createResponse.getBody().getId();

        ResponseEntity<MenuItemDocument> getResponse = restTemplate.getForEntity("/api/menu-items/" + itemId, MenuItemDocument.class);
        assertEquals(200, getResponse.getStatusCodeValue());
        assertNotNull(getResponse.getBody());
        assertEquals("Test Item", getResponse.getBody().getName());
    }

    @Test
    public void testUpdateMenuItem() {
        MenuItemDocument menuItem = MenuItemDocument.builder()
                .name("Original Item")
                .description("Original Description")
                .price(new BigDecimal("15.99"))
                .categoryId("test-category")
                .restaurantId("test-restaurant")
                .build();
        MenuItemDocument savedItem = menuItemService.save(menuItem);

        savedItem.setName("Updated Item");
        savedItem.setDescription("Updated Description");
        savedItem.setPrice(new BigDecimal("18.99"));

        ResponseEntity<MenuItemDocument> updateResponse = restTemplate.exchange(
                "/api/menu-items/" + savedItem.getId(),
                org.springframework.http.HttpMethod.PUT,
                new org.springframework.http.HttpEntity<>(savedItem),
                MenuItemDocument.class
        );

        assertEquals(200, updateResponse.getStatusCodeValue());
        assertNotNull(updateResponse.getBody());
        assertEquals("Updated Item", updateResponse.getBody().getName());
        assertEquals("Updated Description", updateResponse.getBody().getDescription());
        assertEquals(new BigDecimal("18.99"), updateResponse.getBody().getPrice());
    }

    @Test
    public void testDeleteMenuItem() {
        MenuItemDocument menuItem = MenuItemDocument.builder()
                .name("Test Item")
                .description("Test Description")
                .price(new BigDecimal("10.99"))
                .categoryId("test-category")
                .restaurantId("test-restaurant")
                .build();
        MenuItemDocument savedItem = menuItemService.save(menuItem);

        restTemplate.delete("/api/menu-items/" + savedItem.getId());
        assertFalse(menuItemService.findById(savedItem.getId()).isPresent());
    }

    @Test
    public void testGetMenuItemNotFound() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/menu-items/non-existent-id", String.class);
        assertEquals(404, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Menu item not found"));
    }

//    @Test
    public void testSearchMenuItems() {
        MenuItemDocument menuItem = MenuItemDocument.builder()
                .name("Searchable Item")
                .description("Searchable Description")
                .price(new BigDecimal("12.99"))
                .categoryId("search-category")
                .restaurantId("search-restaurant")
                .build();
        menuItemService.save(menuItem);

        MenuSearchCriteria criteria = MenuSearchCriteria.builder()
                .restaurantId("search-restaurant")
                .searchText("Searchable")
                .build();

        ResponseEntity<MenuItemDocument[]> response = restTemplate.postForEntity("/api/menu-items/search", criteria, MenuItemDocument[].class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
    }

    @Test
    public void testGetPopularMenuItems() {
        ResponseEntity<MenuItemDocument[]> response = restTemplate.getForEntity("/api/menu-items/popular/test-restaurant?limit=5", MenuItemDocument[].class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetMenuStatistics() {
        ResponseEntity<Object> response = restTemplate.getForEntity("/api/menu-items/statistics/test-restaurant", Object.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
}