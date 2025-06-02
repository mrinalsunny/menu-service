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
public class ImageInfo {
    @Field("url")
    private String url;

    @Field("alt_text")
    private String altText;

    @Field("is_primary")
    @Builder.Default
    private Boolean isPrimary = false;

    @Field("display_order")
    @Builder.Default
    private Integer displayOrder = 0;

    @Field("image_type")
    private String imageType; // thumbnail, full, hero, etc.
}