package com.categories.backend.listeners;

import com.categories.backend.domain.services.CategoryService;
import com.categories.backend.infra.logs.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.support.AmqpHeaders;
//import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CategoryListener {

    private final CategoryService categoryService;

    /*@RabbitListener(queues = "${c-categories-queue}")
    public void readingMessageFromColaCategories(@Header(AmqpHeaders.CONSUMER_QUEUE) String cola, Message message) {
        try {
            Logger.getLogger(cola).info(String.format("Consumiendo mensaje desde la cola: %s", cola), new String(message.getBody()));
            List<Map<String, Object>> message_map = new ObjectMapper().readValue(message.getBody(), List.class);
            categoryService.getListCategoryFromCola(message_map);
        }
        catch (Exception exception) {
            Logger.getLogger(cola).info(String.format("No fue posible consumir el mensaje desde la cola: %s", cola), new String(message.getBody()));
        }
    }*/
}
