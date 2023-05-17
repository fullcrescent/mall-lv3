package malllv.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "MyPage_table")
@Data
public class MyPage {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long orderId;
    private Long storeId;
    private Long deliveryId;
    private String product;
    private Integer qty;
    private Integer price;
    private String status;
}
