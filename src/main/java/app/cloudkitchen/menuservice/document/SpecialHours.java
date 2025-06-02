package app.cloudkitchen.menuservice.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecialHours {
    @Field("date")
    private LocalDate date;

    @Field("description")
    private String description;

    @Field("is_available")
    private Boolean isAvailable;

    @Field("time_slots")
    private List<TimeSlot> timeSlots;
}