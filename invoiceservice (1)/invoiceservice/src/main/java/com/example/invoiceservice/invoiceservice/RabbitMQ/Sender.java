package com.example.invoiceservice.invoiceservice.RabbitMQ;

import com.example.invoiceservice.invoiceservice.DTOs.RequestCreateInvoiceDTO;
import com.example.invoiceservice.invoiceservice.ExceptionHandler.InvalidRequestException;
import com.example.invoiceservice.invoiceservice.ExceptionHandler.ItemNotFoundException;
import com.example.invoiceservice.invoiceservice.models.Invoice;
import com.example.invoiceservice.invoiceservice.services.InvoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ObjectMapper objectMapper;

    public void send(Invoice invoice) throws JsonProcessingException, ItemNotFoundException, InvalidRequestException {

        Invoice savedInv = null;
        try {
            ResponseEntity<Invoice> savedInvResponse = invoiceService.saveInvoice(invoice);
            savedInv = savedInvResponse.getBody();

            String invJson = objectMapper.writeValueAsString(savedInv);

            this.rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, invJson);
            messagingTemplate.convertAndSend("/topic/notification", "Change status to reserved.");
            System.out.println("Message sent with realEstateId = " + invJson);

        }
        catch (InvalidRequestException ex){
            invoiceService.DeleteInvoiceById(savedInv.getInvoiceId());
            rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, "Error while adding new real estate" + savedInv.getInvoiceId());
            System.out.println("Sent message with status: Error while adding new real estate" + savedInv.getInvoiceId());

        }
        catch (Exception ex) {
            rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, "Error " + savedInv.getInvoiceId());
            System.out.println("Sent message with status: Error " + savedInv.getInvoiceId());
        }
    }
}
