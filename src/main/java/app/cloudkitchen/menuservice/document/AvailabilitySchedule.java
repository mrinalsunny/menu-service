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
public class AvailabilitySchedule {
    @Field("always_available")
    @Builder.Default
    private Boolean alwaysAvailable = true;

    @Field("schedule")
    private List<DaySchedule> schedule;

    @Field("special_hours")
    private List<SpecialHours> specialHours; // holidays, special events
}