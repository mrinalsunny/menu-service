package app.cloudkitchen.menuservice.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient {
    @Field("name")
    private String name;

    @Field("quantity")
    private String quantity;

    @Field("is_optional")
    @Builder.Default
    private Boolean isOptional = false;

    @Field("additional_cost")
    private BigDecimal additionalCost;

    @Field("allergens")
    private List<String> allergens;
}