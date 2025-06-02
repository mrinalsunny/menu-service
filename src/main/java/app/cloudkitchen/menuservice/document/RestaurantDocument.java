package app.cloudkitchen.menuservice.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

// Restaurant Document (if multi-tenant)
@Document(collection = "restaurants")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDocument extends BaseDocument {

    @Id
    private String id;

    @Field("name")
    @Indexed
    private String name;

    @Field("description")
    private String description;

    @Field("cuisine_type")
    private List<String> cuisineType;

    @Field("contact_info")
    private ContactInfo contactInfo;

    @Field("address")
    private Address address;

    @Field("operating_hours")
    private List<DaySchedule> operatingHours;

    @Field("is_active")
    @Builder.Default
    private Boolean isActive = true;

    @Field("settings")
    private RestaurantSettings settings;
}