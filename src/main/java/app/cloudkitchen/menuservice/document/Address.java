package app.cloudkitchen.menuservice.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Field("street")
    private String street;

    @Field("city")
    private String city;

    @Field("state")
    private String state;

    @Field("postal_code")
    private String postalCode;

    @Field("country")
    private String country;

    @Field("coordinates")
    private double[] coordinates; // [longitude, latitude] for 2dsphere index
}