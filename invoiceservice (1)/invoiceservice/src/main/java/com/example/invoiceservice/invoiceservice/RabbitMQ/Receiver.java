package com.example.invoiceservice.invoiceservice.RabbitMQ;

import com.example.invoiceservice.invoiceservice.ExceptionHandler.InvalidRequestException;
import com.example.invoiceservice.invoiceservice.ExceptionHandler.ItemNotFoundException;
import com.example.invoiceservice.invoiceservice.models.Invoice;
import com.example.invoiceservice.invoiceservice.repositories.InvoiceRepository;
import com.example.invoiceservice.invoiceservice.services.InvoiceService;
import com.example.invoiceservice.invoiceservice.services.SendEmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@RabbitListener(queues = "rent-queue")
public class Receiver {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    SendEmailService emailSenderService;


    @Autowired
    private InvoiceRepository invoiceRepository;

    @RabbitHandler
    public void receive(String message) throws Exception {

        String status = message.split(" ")[0];
        Integer realEstateId = parseInt(message.split(" ")[1]);
        //find invoice
        Invoice invoice = invoiceRepository.FindInvoiceForSpecificRealEstate(realEstateId);
        System.out.println("Received message with status: " + message);

        System.out.println("Received message: Real estate ID = " + realEstateId);

        if (status.equals("Ok")) {
            //send email
            emailSenderService.sendEmail(invoice.getUser().getEmail(),
                    "",
                    "Potvrda o uspjesnoj rezervaciji", invoice);
            messagingTemplate.convertAndSend("/topic/notification", "Success. Invoice is created and sent.");
        }


        else {
            invoiceRepository.deleteById(invoice.getInvoiceId());
            messagingTemplate.convertAndSend("/topic/notification", "Failure. Invoice is deleted.");
        }
    }
}
