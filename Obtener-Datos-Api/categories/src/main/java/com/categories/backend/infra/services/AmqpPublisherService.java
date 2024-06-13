package com.categories.backend.infra.services;

import com.categories.backend.domain.entities.Category;
import com.categories.backend.infra.logs.Logger;
import com.categories.backend.infra.util.json.Util_Json;
import lombok.RequiredArgsConstructor;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AmqpPublisherService {

    /*private final AmqpTemplate amqpTemplate;

    @Value("${c-categories-queue}")
    String categories_queue;

    public void publishMessage(String cola, String payload) throws Exception {
        if (cola == null)
            return;
        publish("Publish message", cola, "", new Message(payload.getBytes(), new MessageProperties()));
    }

    private void publish(String action, String exchange, String routingKey, Message message) throws Exception {
        Logger.getLogger("amqp-publisher").info(action, new String(message.getBody()), String.format("Exchange: %s, Routing key: %s", exchange, routingKey));
        amqpTemplate.send(exchange, routingKey, message);
    }

    protected Message newMessage(Object o) throws Exception {
        return new Message(Util_Json.getObjectMapper().writeValueAsBytes(o), new MessageProperties());
    }*/
}
