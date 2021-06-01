package com.example.adminservice.adminservice.Admin.RabbitMQ;

import com.example.adminservice.adminservice.Admin.Dtos.ReservedRealEstate;
import com.example.adminservice.adminservice.Admin.ErrorHandling.InvalidRequestException;
import com.example.adminservice.adminservice.Admin.ErrorHandling.RealEstateNotFoundException;
import com.example.adminservice.adminservice.Admin.Models.RealEstate;
import com.example.adminservice.adminservice.Admin.Repositories.ImageRepository;
import com.example.adminservice.adminservice.Admin.Services.RealEstateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class AdminServiceSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RealEstateService realEstateService;

    @Autowired
    private ImageRepository imageRepository;


    public RealEstate send(RealEstate realEstate) throws InvalidRequestException, RealEstateNotFoundException {
        try{
            var savedRealEstate = realEstateService.saveRealEstate(realEstate).getBody();
            var reservedRealEstate = new ReservedRealEstate(0,savedRealEstate.getRealEstateId(), savedRealEstate.getStaffId(), savedRealEstate.getIsReservated());
            String realEstateJson=objectMapper.writeValueAsString(reservedRealEstate);
            rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, realEstateJson);
            System.out.println("Sent message with status: Ok " + realEstateJson);
            return savedRealEstate;
        }
        catch(InvalidRequestException ex){
            realEstateService.deleteRealEstate(realEstate.getRealEstateId());
            //imageRepository.deleteById(realEstate.getImageModel().getId());
            rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, "Error while adding new real estate" + realEstate.getRealEstateId());
            System.out.println("Sent message with status: Error while adding new real estate" + realEstate.getRealEstateId());
        }
        catch(Exception ex) {
            rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, "Error " + realEstate.getRealEstateId());
            System.out.println("Sent message with status: Error " + realEstate.getRealEstateId());
        }

        return null;
    }

    public void send(Integer realEstateId) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.exchangeName, RabbitMQConfig.routingKey, realEstateId.toString());
        messagingTemplate.convertAndSend("/topic/notification", "Delete request sent.");
        System.out.println("Message sent with realEstateId = " + realEstateId);
    }
}
