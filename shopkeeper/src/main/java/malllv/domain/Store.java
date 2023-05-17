package malllv.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import malllv.ShopkeeperApplication;
import malllv.domain.StoreCancelled;
import malllv.domain.StoreReceived;

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
        StoreReceived storeReceived = new StoreReceived(this);
        storeReceived.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        StoreCancelled storeCancelled = new StoreCancelled(this);
        storeCancelled.publishAfterCommit();
    }

    public static StoreRepository repository() {
        StoreRepository storeRepository = ShopkeeperApplication.applicationContext.getBean(
            StoreRepository.class
        );
        return storeRepository;
    }

    public void completeCookingStore() {
        StoreCookingCompleted storeCookingCompleted = new StoreCookingCompleted(
            this
        );
        storeCookingCompleted.publishAfterCommit();
    }

    public void acceptStore() {
        StoreAccepted storeAccepted = new StoreAccepted(this);
        storeAccepted.publishAfterCommit();
    }

    public void rejectStore() {
        StoreRejected storeRejected = new StoreRejected(this);
        storeRejected.publishAfterCommit();
    }

    public void startCookingStore() {
        StoreCookingStarted storeCookingStarted = new StoreCookingStarted(this);
        storeCookingStarted.publishAfterCommit();
    }

    public static void receiveStore(OrderPlaced orderPlaced) {
        /** Example 1:  new item 
        Store store = new Store();
        repository().save(store);

        StoreReceived storeReceived = new StoreReceived(store);
        storeReceived.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(store->{
            
            store // do something
            repository().save(store);

            StoreReceived storeReceived = new StoreReceived(store);
            storeReceived.publishAfterCommit();

         });
        */

    }

    public static void cancelStore(OrderCancelled orderCancelled) {
        /** Example 1:  new item 
        Store store = new Store();
        repository().save(store);

        StoreCancelled storeCancelled = new StoreCancelled(store);
        storeCancelled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderCancelled.get???()).ifPresent(store->{
            
            store // do something
            repository().save(store);

            StoreCancelled storeCancelled = new StoreCancelled(store);
            storeCancelled.publishAfterCommit();

         });
        */

    }
}
