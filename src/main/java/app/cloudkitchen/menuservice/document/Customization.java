package app.cloudkitchen.menuservice.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customization {
    @Field("id")
    private String id;

    @Field("name")
    private String name;

    @Field("type")
    private CustomizationType type; // SINGLE_SELECT, MULTI_SELECT, TEXT_INPUT

    @Field("is_required")
    @Builder.Default
    private Boolean isRequired = false;

    @Field("max_selections")
    private Integer maxSelections;

    @Field("options")
    private List<CustomizationOption> options;
}