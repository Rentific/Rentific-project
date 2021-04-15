package com.example.invoiceservice.invoiceservice;

import com.example.invoiceservice.invoiceservice.ExceptionHandler.GlobalExceptionHandler;
import com.example.invoiceservice.invoiceservice.ExceptionHandler.InvalidRequestException;
import com.example.invoiceservice.invoiceservice.ExceptionHandler.ItemNotFoundException;
import com.example.invoiceservice.invoiceservice.controllers.UserController;
import com.example.invoiceservice.invoiceservice.models.Invoice;
import com.example.invoiceservice.invoiceservice.models.RealEstate;
import com.example.invoiceservice.invoiceservice.models.User;
import com.example.invoiceservice.invoiceservice.services.InvoiceService;
import com.example.invoiceservice.invoiceservice.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

public class InvoiceControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private InvoiceService invoiceService;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.standaloneSetup(UserController.class)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void findInvoiceById_ReturnOkWithResult() throws Exception {
        RealEstate realEstate = new RealEstate() ;
        realEstate.setRealEstateId(1);
        User user = new User() ;
        user.setUserId(1);


        Invoice newInvoice = new Invoice(new Date(), realEstate, user);
        newInvoice.setInvoiceId(1);

        given(invoiceService.findInvoicesById(anyInt())).willReturn(new ResponseEntity<>(newInvoice, HttpStatus.OK));

        mvc.perform(get("/invoice/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.invoiceId", is(newInvoice.getInvoiceId())));

    }


    @Test
    public void findInvoiceById_IdIsInvalid_ERRMessage() throws Exception {

        given(invoiceService.findInvoicesById(anyInt()))
                .willThrow(new InvalidRequestException("Sent Id is not valid."));

        mvc.perform(get("/invoice-user/-123"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void findInvoiceById_InvoiceIsNotFound_ERRMessage() throws Exception {

        given(invoiceService.findInvoicesById(anyInt()))
                .willThrow(new ItemNotFoundException("Object with sent ID is not found."));

        mvc.perform(get("/invoice/1001"))
                .andExpect(status().isNotFound());
    }

   /*@Test
    public void addNewInvoice_StatusOkWithResult() throws Exception {
        List<Invoice> inv = new ArrayList<> ();
        RealEstate newRealEstate = new RealEstate("Grbavica", 100.00, inv);
        User newUser = new User("QA", "Surname",inv);
        Invoice newInvoice = new Invoice(new Date(), newRealEstate, newUser);
        newRealEstate.setRealEstateId(1);

        given(invoiceService.saveInvoice(newInvoice)).willReturn(new ResponseEntity<>(newInvoice, HttpStatus.OK));

        mvc.perform(post("/invoice/add")
                .content(new ObjectMapper().writeValueAsString(newUser))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    */

}
