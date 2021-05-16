package com.example.rentservice.rentservice.rabbitmq;

import com.example.rentservice.rentservice.ErrorHandling.InvalidRequestException;
import com.example.rentservice.rentservice.ErrorHandling.ObjectNotFoundException;
import com.example.rentservice.rentservice.Models.RealEstate;
import com.example.rentservice.rentservice.Repositories.RealEstateRepository;
import com.example.rentservice.rentservice.Services.RealEstateService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static java.lang.Integer.parseInt;

@RabbitListener(queues = "real-estate-queue")
public class Receiver {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private RealEstateService realEstateService;

    @RabbitHandler
    public void receive(String message) throws InvalidRequestException, ObjectNotFoundException {
        Integer realEstateId = Integer.parseInt(message);
        System.out.println("Received message = " + realEstateId);
        var realEstateToDelete = realEstateService.findRealEstateById(realEstateId).getBody();
        try {
            realEstateService.findRealEstateById(realEstateId).getBody();
            realEstateService.deleteRealEstate(realEstateId);

            rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, "Ok " + realEstateId);
            System.out.println("Sent message with status: Ok " + realEstateId);

        }
        catch (ObjectNotFoundException ex) {
            realEstateService.saveRealEstate(realEstateToDelete);
            rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, "Error " + realEstateId);
            System.out.println("Sent message with status: Error " + realEstateId);
        }
        catch (Exception ex) {
            rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, "Error " + realEstateId);
            System.out.println("Sent message with status: Error " + realEstateId);
        }

    }

}
