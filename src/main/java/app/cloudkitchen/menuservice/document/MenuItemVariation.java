package app.cloudkitchen.menuservice.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemVariation {
    @Field("id")
    private String id;

    @Field("name")
    private String name; // Small, Medium, Large, etc.

    @Field("price")
    private BigDecimal price;

    @Field("description")
    private String description;

    @Field("is_default")
    @Builder.Default
    private Boolean isDefault = false;

    @Field("preparation_time")
    private Integer preparationTimeMinutes;

    @Field("nutrition_info")
    private NutritionInfo nutritionInfo;
}