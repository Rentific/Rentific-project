package com.example.adminservice.adminservice.Admin.RabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class AdminServiceSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void send(Integer realEstateId) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, realEstateId.toString());
        messagingTemplate.convertAndSend("/topic/notification", "Delete request sent.");
        System.out.println("Message sent with realEstateId = " + realEstateId);
    }
}
