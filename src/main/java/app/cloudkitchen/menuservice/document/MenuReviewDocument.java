package app.cloudkitchen.menuservice.document;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

// Menu Review Document (separate collection for better performance)
@Document(collection = "menu_reviews")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@CompoundIndex(def = "{'menu_item_id': 1, 'customer_id': 1}", unique = true)
public class MenuReviewDocument extends BaseDocument {

    @Id
    private String id;

    @Field("menu_item_id")
    @Indexed
    private String menuItemId;

    @Field("customer_id")
    @Indexed
    private String customerId;

    @Field("customer_name")
    private String customerName;

    @Field("rating")
    @Min(1)
    @Max(5)
    private Integer rating;

    @Field("review_text")
    private String reviewText;

    @Field("is_verified_purchase")
    @Builder.Default
    private Boolean isVerifiedPurchase = false;

    @Field("helpful_count")
    @Builder.Default
    private Integer helpfulCount = 0;

    @Field("restaurant_id")
    @Indexed
    private String restaurantId;
}
