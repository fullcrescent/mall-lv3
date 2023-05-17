package malllv.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import malllv.RiderApplication;
import malllv.domain.DeliveryAdded;

@Entity
@Table(name = "Delivery_table")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long storeId;

    private Long orderId;

    private String product;

    private Integer qty;

    private Integer price;

    private String status;

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = RiderApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public void startDelivery() {
        this.setStatus("startDelivery");
        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();
    }

    public void completeDelivery() {
        this.setStatus("completeDelivery");
        DeliveryCompleted deliveryCompleted = new DeliveryCompleted(this);
        deliveryCompleted.publishAfterCommit();
    }

    public static void addDelivery(
        StoreCookingCompleted storeCookingCompleted
    ) {
        Delivery delivery = new Delivery();

        delivery.setStoreId(storeCookingCompleted.getId());
        delivery.setOrderId(storeCookingCompleted.getOrderId());
        delivery.setProduct(storeCookingCompleted.getProduct());
        delivery.setQty(storeCookingCompleted.getQty());
        delivery.setPrice(storeCookingCompleted.getPrice());
        delivery.setStatus("waitDelivery");

        repository().save(delivery);

        DeliveryAdded deliveryAdded = new DeliveryAdded(delivery);
        deliveryAdded.publishAfterCommit();
    }
}
