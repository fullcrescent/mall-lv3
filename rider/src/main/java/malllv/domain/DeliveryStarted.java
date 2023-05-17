package malllv.domain;

import java.util.*;
import lombok.*;
import malllv.domain.*;
import malllv.infra.AbstractEvent;

@Data
@ToString
public class DeliveryStarted extends AbstractEvent {

    private Long id;
    private Long storeId;
    private Long orderId;
    private String product;
    private Integer qty;
    private Integer price;
    private String status;

    public DeliveryStarted(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryStarted() {
        super();
    }
}
