package com.example.adminservice.adminservice.Admin.RabbitMQ;

import com.example.adminservice.adminservice.Admin.Repositories.RealEstateRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static java.lang.Integer.parseInt;

@RabbitListener(queues = "rent-queue")
public class AdminServiceReceiver {
    @Autowired
    private RealEstateRepository realEstateRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RabbitHandler
    public void receive(String message) {
        try {
            String status = message.split(" ")[0];
            Integer realEstateId = parseInt(message.split(" ")[1]);
            System.out.println("Received message with status: " + message);

            if (status.equals("Ok")) {
                realEstateRepository.deleteById(realEstateId);
                messagingTemplate.convertAndSend("/topic/notification", "Success. Real estate is deleted.");
            }
        } catch (Exception e) {
            System.out.println("Error! " + e);
        }

    }
}
