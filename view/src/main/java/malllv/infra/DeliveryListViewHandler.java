package malllv.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import malllv.config.kafka.KafkaProcessor;
import malllv.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class DeliveryListViewHandler {

    @Autowired
    private DeliveryListRepository deliveryListRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCookingCompleted_then_CREATE_1(
        @Payload CookingCompleted cookingCompleted
    ) {
        try {
            if (!cookingCompleted.validate()) return;

            // view 객체 생성
            DeliveryList deliveryList = new DeliveryList();
            // view 객체에 이벤트의 Value 를 set 함
            deliveryList.setStoreId(cookingCompleted.getId());
            deliveryList.setOrderId(cookingCompleted.getOrderId());
            deliveryList.setProduct(cookingCompleted.getProduct());
            deliveryList.setQty(cookingCompleted.getQty());
            deliveryList.setPrice(cookingCompleted.getPrice());
            deliveryList.setStatus(cookingCompleted.getStatus());
            // view 레파지 토리에 save
            deliveryListRepository.save(deliveryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
