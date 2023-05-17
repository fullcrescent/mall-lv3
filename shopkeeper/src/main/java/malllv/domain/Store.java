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

    public static StoreRepository repository() {
        StoreRepository storeRepository = ShopkeeperApplication.applicationContext.getBean(
            StoreRepository.class
        );
        return storeRepository;
    }

    public void completeCookingStore() {
        this.setStatus("completeCookingStore");
        StoreCookingCompleted storeCookingCompleted = new StoreCookingCompleted(
            this
        );
        storeCookingCompleted.publishAfterCommit();
    }

    public void acceptStore() {
        this.setStatus("acceptStore");
        StoreAccepted storeAccepted = new StoreAccepted(this);
        storeAccepted.publishAfterCommit();
    }

    public void rejectStore() {
        this.setStatus("rejectStore");
        StoreRejected storeRejected = new StoreRejected(this);
        storeRejected.publishAfterCommit();
    }

    public void startCookingStore() {
        this.setStatus("startCookingStore");
        StoreCookingStarted storeCookingStarted = new StoreCookingStarted(this);
        storeCookingStarted.publishAfterCommit();
    }

    public static void receiveStore(OrderPlaced orderPlaced) {
        Store store = new Store();

        store.setOrderId(orderPlaced.getId());
        store.setProduct(orderPlaced.getProduct());
        store.setQty(orderPlaced.getQty());
        store.setPrice(orderPlaced.getPrice());

        repository().save(store);

        StoreReceived storeReceived = new StoreReceived(store);
        storeReceived.publishAfterCommit();
    }

    public static void cancelStore(OrderCancelled orderCancelled) {
        repository().findByOrderId(orderCancelled.getId()).ifPresent(store->{
            store.setStatus("cancelStore");
            repository().save(store);

            StoreCancelled storeCancelled = new StoreCancelled(store);
            storeCancelled.publishAfterCommit();
         });
    }
}
