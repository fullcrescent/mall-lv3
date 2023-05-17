package malllv.domain;

import java.util.*;
import lombok.*;
import malllv.domain.*;
import malllv.infra.AbstractEvent;

@Data
@ToString
public class StoreAccepted extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String product;
    private Integer qty;
    private Integer price;
    private String status;

    public StoreAccepted(Store aggregate) {
        super(aggregate);
    }

    public StoreAccepted() {
        super();
    }
}
