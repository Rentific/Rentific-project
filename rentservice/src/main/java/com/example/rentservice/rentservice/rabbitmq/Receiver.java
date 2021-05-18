package com.example.rentservice.rentservice.rabbitmq;

import com.example.rentservice.rentservice.ErrorHandling.InvalidRequestException;
import com.example.rentservice.rentservice.ErrorHandling.ObjectNotFoundException;
import com.example.rentservice.rentservice.Models.RealEstate;
import com.example.rentservice.rentservice.Repositories.RealEstateRepository;
import com.example.rentservice.rentservice.Services.RealEstateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static java.lang.Integer.parseInt;

public class Receiver {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private RealEstateService realEstateService;
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitHandler
    @RabbitListener(queues = "real-estate-queue")
    public void receive(String message) throws InvalidRequestException, ObjectNotFoundException, JsonProcessingException {
        if(message.contains("customerId")){

            RealEstate newRealEstate = objectMapper.readValue(message, RealEstate.class);
            try{
                realEstateService.saveRealEstate(newRealEstate);
                System.out.println("Sent message with status: Ok " + newRealEstate);
            }
            catch(Exception ex){
                rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, "Error " + newRealEstate);
                System.out.println("Sent message with status: Error " + newRealEstate);
            }

        }
        else{
            Integer realEstateId = Integer.parseInt(message);
            System.out.println("Received message = " + realEstateId);
            var realEstate = realEstateService.findRealEstateById(realEstateId).getBody();
            try {

                realEstateService.deleteRealEstate(realEstateId);

                rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, "Ok " + realEstateId);
                System.out.println("Sent message with status: Ok " + realEstateId);

            }
            catch (ObjectNotFoundException ex) {
                realEstateService.saveRealEstate(realEstate);
                rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, "Error " + realEstateId);
                System.out.println("Sent message with status: Error " + realEstateId);
            }
            catch (Exception ex) {
                rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, "Error " + realEstateId);
                System.out.println("Sent message with status: Error " + realEstateId);
            }
        }
    }

}
