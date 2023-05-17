package malllv.domain;

import java.util.*;
import lombok.*;
import malllv.domain.*;
import malllv.infra.AbstractEvent;

@Data
@ToString
public class DeliveryAdded extends AbstractEvent {

    private Long id;
    private Long storeId;
    private Long orderId;
    private String product;
    private Integer qty;
    private Integer price;
    private String status;

    public DeliveryAdded(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryAdded() {
        super();
    }
}
