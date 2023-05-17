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

    @PostPersist
    public void onPostPersist() {
        DeliveryAdded deliveryAdded = new DeliveryAdded(this);
        deliveryAdded.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = RiderApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public void startDelivery() {
        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();
    }

    public void completeDelivery() {
        DeliveryCompleted deliveryCompleted = new DeliveryCompleted(this);
        deliveryCompleted.publishAfterCommit();
    }

    public static void addDelivery(
        StoreCookingCompleted storeCookingCompleted
    ) {
        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        DeliveryAdded deliveryAdded = new DeliveryAdded(delivery);
        deliveryAdded.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(storeCookingCompleted.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            DeliveryAdded deliveryAdded = new DeliveryAdded(delivery);
            deliveryAdded.publishAfterCommit();

         });
        */

    }
}
