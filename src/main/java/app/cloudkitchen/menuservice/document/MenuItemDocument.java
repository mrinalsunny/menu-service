package app.cloudkitchen.menuservice.document;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.List;

// Main Menu Item Document
@Document(collection = "menu_items")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@CompoundIndex(def = "{'restaurant_id': 1, 'category_id': 1, 'is_available': 1}")
@CompoundIndex(def = "{'name': 'text', 'description': 'text', 'tags': 'text'}")
public class MenuItemDocument extends BaseDocument {

    @Id
    private String id;

    @NotBlank
    @Field("name")
    @Indexed
    private String name;

    @Field("display_name")
    private String displayName;

    @NotBlank
    @Field("description")
    private String description;

    @Field("short_description")
    private String shortDescription;

    @NotNull
    @DecimalMin("0.01")
    @Field("price")
    private BigDecimal price;

    @Field("original_price")
    private BigDecimal originalPrice; // For discount calculations

    @Field("cost_price")
    private BigDecimal costPrice; // For profit margin calculations

    @Field("currency")
    @Builder.Default
    private String currency = "USD";

    @NotBlank
    @Field("category_id")
    @Indexed
    private String categoryId;

    @Field("category_name")
    private String categoryName; // Denormalized for faster queries

    @Field("restaurant_id")
    @Indexed
    private String restaurantId;

    @Field("is_available")
    @Builder.Default
    private Boolean isAvailable = true;

    @Field("preparation_time")
    @Min(1)
    private Integer preparationTimeMinutes;

    @Field("cooking_instructions")
    private String cookingInstructions;

    // Nutritional Information
    @Field("nutrition_info")
    private NutritionInfo nutritionInfo;

    // Dietary Information
    @Field("dietary_info")
    private List<String> dietaryInfo;

    // Allergen Information
    @Field("allergens")
    private List<String> allergens;

    // Spice Level
    @Field("spice_level")
    private SpiceLevel spiceLevel;

    // Images
    @Field("images")
    private List<ImageInfo> images;

    // Ingredients and Customizations
    @Field("ingredients")
    private List<Ingredient> ingredients;

    @Field("customizations")
    private List<Customization> customizations;

    // Variations (sizes, portions, etc.)
    @Field("variations")
    private List<MenuItemVariation> variations;

    // Tags for search and filtering
    @Field("tags")
    private List<String> tags;

    // Availability schedule
    @Field("availability_schedule")
    private AvailabilitySchedule availabilitySchedule;

    // Popularity and ratings
    @Field("rating")
    @Builder.Default
    private Double rating = 0.0;

    @Field("review_count")
    @Builder.Default
    private Integer reviewCount = 0;

    @Field("popularity_score")
    @Builder.Default
    private Double popularityScore = 0.0;

    @Field("order_count")
    @Builder.Default
    private Integer orderCount = 0;

    // Inventory tracking
    @Field("track_inventory")
    @Builder.Default
    private Boolean trackInventory = false;

    @Field("stock_quantity")
    private Integer stockQuantity;

    @Field("low_stock_threshold")
    private Integer lowStockThreshold;

    // Display settings
    @Field("display_order")
    @Builder.Default
    private Integer displayOrder = 0;

    @Field("is_featured")
    @Builder.Default
    private Boolean isFeatured = false;

    @Field("is_popular")
    @Builder.Default
    private Boolean isPopular = false;

    @Field("is_new")
    @Builder.Default
    private Boolean isNew = false;
}