package app.cloudkitchen.menuservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewStatistics {
    private Double avgRating;
    private Integer totalReviews;
}