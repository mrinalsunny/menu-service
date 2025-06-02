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
public class NutritionInfo {
    @Field("calories")
    private Integer calories;

    @Field("protein_g")
    private Double proteinGrams;

    @Field("carbs_g")
    private Double carbsGrams;

    @Field("fat_g")
    private Double fatGrams;

    @Field("fiber_g")
    private Double fiberGrams;

    @Field("sugar_g")
    private Double sugarGrams;

    @Field("sodium_mg")
    private Double sodiumMg;

    @Field("cholesterol_mg")
    private Double cholesterolMg;

    @Field("serving_size")
    private String servingSize;
}