package app.cloudkitchen.menuservice.document;


import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

// Category Document
@Document(collection = "categories")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@CompoundIndex(def = "{'name': 1, 'restaurant_id': 1}", unique = true)
public class CategoryDocument extends BaseDocument {

    @Id
    private String id;

    @NotBlank
    @Field("name")
    @Indexed
    private String name;

    @Field("display_name")
    private String displayName;

    @Field("description")
    private String description;

    @Field("display_order")
    private Integer displayOrder = 0;

    @Field("is_active")
    @Builder.Default
    private Boolean isActive = true;

    @Field("restaurant_id")
    @Indexed
    private String restaurantId;

    @Field("image_url")
    private String imageUrl;

    @Field("icon")
    private String icon;

    @Field("color_code")
    private String colorCode;

    @Field("dietary_info")
    private List<String> dietaryInfo; // vegetarian, vegan, gluten-free, etc.

    @Field("availability_schedule")
    private AvailabilitySchedule availabilitySchedule;
}
