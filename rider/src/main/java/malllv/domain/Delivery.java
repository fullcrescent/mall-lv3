package malllv.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import malllv.RiderApplication;
import malllv.domain.DeliveryAdded;
import malllv.domain.DeliveryCompleted;
import malllv.domain.DeliveryStarted;

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
        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();

        DeliveryCompleted deliveryCompleted = new DeliveryCompleted(this);
        deliveryCompleted.publishAfterCommit();

        DeliveryAdded deliveryAdded = new DeliveryAdded(this);
        deliveryAdded.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = RiderApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public static void addDelivery(CookingCompleted cookingCompleted) {
        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        DeliveryAdded deliveryAdded = new DeliveryAdded(delivery);
        deliveryAdded.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(cookingCompleted.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            DeliveryAdded deliveryAdded = new DeliveryAdded(delivery);
            deliveryAdded.publishAfterCommit();

         });
        */

    }
}
