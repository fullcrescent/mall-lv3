package malllv.infra;

import malllv.domain.*;
import malllv.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MyPageViewHandler {

    @Autowired
    private MyPageRepository myPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderPlaced_then_CREATE_1 (@Payload OrderPlaced orderPlaced) {
        try {

            if (!orderPlaced.validate()) return;

            // view 객체 생성
            MyPage myPage = new MyPage();
            // view 객체에 이벤트의 Value 를 set 함
            myPage.setOrderId(orderPlaced.getId());
            myPage.setProduct(orderPlaced.getProduct());
            myPage.setQty(orderPlaced.getQty());
            myPage.setPrice(orderPlaced.getPrice());
            myPage.setStatus(Order Start);
            // view 레파지 토리에 save
            myPageRepository.save(myPage);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenStoreCookingCompleted_then_UPDATE_1(@Payload StoreCookingCompleted storeCookingCompleted) {
        try {
            if (!storeCookingCompleted.validate()) return;
                // view 객체 조회

                List<MyPage> myPageList = myPageRepository.findByOrderId(storeCookingCompleted.getOrderId());
                for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setStoreId(storeCookingCompleted.getId());
                    myPage.setStatus(Cooking Complete);
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryCompleted_then_UPDATE_2(@Payload DeliveryCompleted deliveryCompleted) {
        try {
            if (!deliveryCompleted.validate()) return;
                // view 객체 조회

                List<MyPage> myPageList = myPageRepository.findByOrderId(deliveryCompleted.getOrderId());
                for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setDeliveryId(deliveryCompleted.getId());
                    myPage.setStatus(Delivery Complete);
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}

