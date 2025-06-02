package app.cloudkitchen.menuservice.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantSettings {
    @Field("default_currency")
    @Builder.Default
    private String defaultCurrency = "USD";

    @Field("tax_rate")
    private BigDecimal taxRate;

    @Field("service_charge")
    private BigDecimal serviceCharge;

    @Field("allow_customizations")
    @Builder.Default
    private Boolean allowCustomizations = true;

    @Field("auto_accept_orders")
    @Builder.Default
    private Boolean autoAcceptOrders = false;
}