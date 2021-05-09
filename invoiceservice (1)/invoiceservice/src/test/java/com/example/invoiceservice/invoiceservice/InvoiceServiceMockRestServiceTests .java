package com.example.invoiceservice.invoiceservice;

import com.example.invoiceservice.invoiceservice.DTOs.UserDTO;
import com.example.invoiceservice.invoiceservice.models.User;
import com.example.invoiceservice.invoiceservice.services.InvoiceService;
import com.example.invoiceservice.invoiceservice.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
/*
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringTestConfig.class)
public class EmployeeServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private InvoiceService invService;

    @Test
    public void creatingInvoice_GetIsCalledForUserAndRealEstate_shouldReturnMockedObject() {
      //  User u = new User(1, "Sanida", "Test", "sanidatest@getnada.com");


      //  Mockito
        //        .when(restTemplate.getForEntity(“http://localhost:8083/employee/E001”, UserDTO.class))
          //      .thenReturn(new ResponseEntity(uDto, HttpStatus.OK));

        //Employee employee = empService.getEmployee(id);
        //Assert.assertEquals(emp, employee);
    }
}*/
