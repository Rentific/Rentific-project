/*
package com.example.adminservice.adminservice.Admin.RabbitMQ;

import com.example.adminservice.adminservice.Admin.ErrorHandling.InvalidRequestException;
import com.example.adminservice.adminservice.Admin.ErrorHandling.RealEstateNotFoundException;
import com.example.adminservice.adminservice.Admin.Services.RealEstateService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static java.lang.Integer.parseInt;

@RabbitListener(queues = "rent-queue")
public class Receiver {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private RealEstateService realEstateService;

    @RabbitHandler
    public void receive(String message) throws InvalidRequestException, RealEstateNotFoundException {
        Integer realEstateId = Integer.parseInt(message);
        System.out.println("Received message = " + realEstateId);
        var realEstateToDelete = realEstateService.findRealEstateById(realEstateId).getBody();
        try {
            realEstateService.findRealEstateById(realEstateId).getBody();
            realEstateService.deleteRealEstate(realEstateId);

            rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, "Ok " + realEstateId);
            System.out.println("Sent message with status: Ok " + realEstateId);

        }
        catch (RealEstateNotFoundException ex) {
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
*/
