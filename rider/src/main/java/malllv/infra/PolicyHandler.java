package malllv.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import malllv.config.kafka.KafkaProcessor;
import malllv.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='StoreCookingCompleted'"
    )
    public void wheneverStoreCookingCompleted_AddDelivery(
        @Payload StoreCookingCompleted storeCookingCompleted
    ) {
        StoreCookingCompleted event = storeCookingCompleted;
        System.out.println(
            "\n\n##### listener AddDelivery : " + storeCookingCompleted + "\n\n"
        );

        // Sample Logic //
        Delivery.addDelivery(event);
    }
}
