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
    public void whenStoreCookingCompleted_then_CREATE_1(
        @Payload StoreCookingCompleted storeCookingCompleted
    ) {
        try {
            if (!storeCookingCompleted.validate()) return;

            // view 객체 생성
            DeliveryList deliveryList = new DeliveryList();
            // view 객체에 이벤트의 Value 를 set 함
            deliveryList.setStoreId(storeCookingCompleted.getId());
            deliveryList.setOrderId(storeCookingCompleted.getOrderId());
            deliveryList.setProduct(storeCookingCompleted.getProduct());
            deliveryList.setQty(storeCookingCompleted.getQty());
            deliveryList.setPrice(storeCookingCompleted.getPrice());
            deliveryList.setStatus(storeCookingCompleted.getStatus());
            // view 레파지 토리에 save
            deliveryListRepository.save(deliveryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
