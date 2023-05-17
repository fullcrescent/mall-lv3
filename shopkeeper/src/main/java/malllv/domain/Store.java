package malllv.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import malllv.ShopkeeperApplication;
import malllv.domain.CookingCompleted;
import malllv.domain.CookingStarted;
import malllv.domain.OrderAccepted;
import malllv.domain.OrderReceived;
import malllv.domain.OrderRejected;

@Entity
@Table(name = "Store_table")
@Data
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private String product;

    private Integer qty;

    private Integer price;

    private String status;

    @PostPersist
    public void onPostPersist() {
        OrderReceived orderReceived = new OrderReceived(this);
        orderReceived.publishAfterCommit();

        OrderAccepted orderAccepted = new OrderAccepted(this);
        orderAccepted.publishAfterCommit();

        OrderRejected orderRejected = new OrderRejected(this);
        orderRejected.publishAfterCommit();

        CookingStarted cookingStarted = new CookingStarted(this);
        cookingStarted.publishAfterCommit();

        CookingCompleted cookingCompleted = new CookingCompleted(this);
        cookingCompleted.publishAfterCommit();
    }

    public static StoreRepository repository() {
        StoreRepository storeRepository = ShopkeeperApplication.applicationContext.getBean(
            StoreRepository.class
        );
        return storeRepository;
    }

    public static void receiveOrder(OrderPlaced orderPlaced) {
        /** Example 1:  new item 
        Store store = new Store();
        repository().save(store);

        OrderReceived orderReceived = new OrderReceived(store);
        orderReceived.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(store->{
            
            store // do something
            repository().save(store);

            OrderReceived orderReceived = new OrderReceived(store);
            orderReceived.publishAfterCommit();

         });
        */

    }
}
