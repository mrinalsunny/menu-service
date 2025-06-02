package app.cloudkitchen.menuservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuStatistics {
    private Integer totalItems;
    private Integer availableItems;
    private BigDecimal avgPrice;
}