package app.cloudkitchen.menuservice.dto;

import app.cloudkitchen.menuservice.document.SpiceLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuSearchCriteria {
    private String restaurantId;
    private List<String> categoryIds;
    private Boolean isAvailable;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private List<String> dietaryInfo;
    private List<String> excludeAllergens;
    private SpiceLevel maxSpiceLevel;
    private String searchText;
    private String sortBy;
    private SortDirection sortDirection;
    private Integer page;
    private Integer size;
}