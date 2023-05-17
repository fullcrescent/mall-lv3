package malllv.domain;

import java.util.*;
import lombok.*;
import malllv.domain.*;
import malllv.infra.AbstractEvent;

@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String product;
    private Integer qty;
    private Integer price;

    public OrderPlaced(Order aggregate) {
        super(aggregate);
    }

    public OrderPlaced() {
        super();
    }
}
