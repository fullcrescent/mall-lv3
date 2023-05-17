package malllv.domain;

import java.util.*;
import lombok.Data;
import malllv.infra.AbstractEvent;

@Data
public class DeliveryCompleted extends AbstractEvent {

    private Long id;
    private Long storeId;
    private Long orderId;
    private String product;
    private Integer qty;
    private Integer price;
    private String status;
}
