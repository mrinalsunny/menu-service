package app.cloudkitchen.menuservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PopularMenuItem {
    private String name;
    private BigDecimal price;
    private Double popularityScore;
    private Integer orderCount;
    private Double rating;
}