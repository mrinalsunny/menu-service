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
public class CustomizationOption {
    @Field("id")
    private String id;

    @Field("name")
    private String name;

    @Field("additional_cost")
    @Builder.Default
    private BigDecimal additionalCost = BigDecimal.ZERO;

    @Field("is_default")
    @Builder.Default
    private Boolean isDefault = false;
}