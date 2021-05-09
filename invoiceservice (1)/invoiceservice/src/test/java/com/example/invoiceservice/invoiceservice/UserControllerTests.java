package com.example.invoiceservice.invoiceservice;

import com.example.invoiceservice.invoiceservice.controllers.UserController;
import com.example.invoiceservice.invoiceservice.models.Invoice;
import com.example.invoiceservice.invoiceservice.models.User;
import com.example.invoiceservice.invoiceservice.ExceptionHandler.InvalidRequestException;
import com.example.invoiceservice.invoiceservice.ExceptionHandler.ItemNotFoundException;
import com.example.invoiceservice.invoiceservice.ExceptionHandler.GlobalExceptionHandler;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.standaloneSetup(UserController.class)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }


    @Test
    public void findUserById_ReturnOkWithResult() throws Exception {
        List<Invoice> inv = new ArrayList<> ();

        User newUser = new User("QATest", "QASurname", "sanida@hotmail.com", inv);
        newUser.setUserId(1);

        given(userService.findUserById(anyInt())).willReturn(new ResponseEntity<>(newUser, HttpStatus.OK));

        mvc.perform(get("/invoice-user/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("QATest")))
                .andExpect(jsonPath("$.lastName", is("QASurname")));


    }


    @Test
    public void findUserById_IdIsInvalid_ERRMessage() throws Exception {

        given(userService.findUserById(anyInt()))
                .willThrow(new InvalidRequestException("Sent Id is not valid."));

        mvc.perform(get("/invoice-user/-123"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void findUserById_UserIsNotFound_ERRMessage() throws Exception {

        given(userService.findUserById(anyInt()))
                .willThrow(new ItemNotFoundException("Object with sent ID is not found."));

        mvc.perform(get("/invoice-user/100"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void addNewUser_StatusOkWithResult() throws Exception {
        List<Invoice> inv = new ArrayList<> ();

        User newUser = new User("QA", "Surname", "sanida@hotmail.com", inv);
        newUser.setUserId(1);

        given(userService.saveUser(newUser)).willReturn(new ResponseEntity<>(newUser, HttpStatus.OK));

        mvc.perform(post("/invoice-user/add")
                .content(new ObjectMapper().writeValueAsString(newUser))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

   @Test
    public void addNewUser_RequestIsInvalid_ERRMessage() throws Exception {
        List<Invoice> inv = new ArrayList<> ();
        User newUser = new User("QA 392!#)$=", "Ex 3z2 093", "sanida@hotmail.com", inv);

        given(userService.saveUser(ArgumentMatchers.<User>any()))
                .willThrow(new InvalidRequestException("Wrong format of properties: First Name, Last Name."));

        mvc.perform(post("/invoice-user/add")
                .content(new ObjectMapper().writeValueAsString(newUser))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }


}