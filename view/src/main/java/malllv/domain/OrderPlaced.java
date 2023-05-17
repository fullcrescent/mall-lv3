package malllv.domain;

import java.util.*;
import lombok.Data;
import malllv.infra.AbstractEvent;

@Data
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String product;
    private Integer qty;
    private Integer price;
}
