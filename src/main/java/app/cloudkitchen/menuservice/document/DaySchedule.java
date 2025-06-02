package app.cloudkitchen.menuservice.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.DayOfWeek;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DaySchedule {
    @Field("day_of_week")
    private DayOfWeek dayOfWeek;

    @Field("is_available")
    @Builder.Default
    private Boolean isAvailable = true;

    @Field("time_slots")
    private List<TimeSlot> timeSlots;
}