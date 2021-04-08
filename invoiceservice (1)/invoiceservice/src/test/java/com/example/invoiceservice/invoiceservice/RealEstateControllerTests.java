package com.example.invoiceservice.invoiceservice;

import com.example.invoiceservice.invoiceservice.ExceptionHandler.GlobalExceptionHandler;
import com.example.invoiceservice.invoiceservice.ExceptionHandler.InvalidRequestException;
import com.example.invoiceservice.invoiceservice.ExceptionHandler.ItemNotFoundException;
import com.example.invoiceservice.invoiceservice.controllers.UserController;
import com.example.invoiceservice.invoiceservice.models.Invoice;
<<<<<<< HEAD
import com.example.invoiceservice.invoiceservice.models.RealEstate;
=======
>>>>>>> fbe799c4da015c97988314d0f6271c80e6bc18c9
import com.example.invoiceservice.invoiceservice.models.User;
import com.example.invoiceservice.invoiceservice.services.RealEstateService;
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

public class RealEstateControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private RealEstateService realEstateService;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.standaloneSetup(UserController.class)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

<<<<<<< HEAD
    @Test
    public void findRealEstateById_ReturnOkWithResult() throws Exception {
        List<Invoice> inv = new ArrayList<>();

        RealEstate newRealEstate = new RealEstate("Grbavica", 100.00, inv);
        newRealEstate.setRealEstateId(1);

        given(realEstateService.findRealEstateById(anyInt())).willReturn(new ResponseEntity<>(newRealEstate, HttpStatus.OK));

        mvc.perform(get("/invoice-realEstate/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Grbavica")))
                .andExpect(jsonPath("$.price", is(100)));
=======
    /*@Test
    public void findUserById_ReturnOkWithResult() throws Exception {
        List<Invoice> inv = new ArrayList<>();

        User newUser = new User("QATest", "QASurname", inv);
        newUser.setUserId(1);

        given(userService.findUserById(anyInt())).willReturn(new ResponseEntity<>(newUser, HttpStatus.OK));

        mvc.perform(get("/invoice-user/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("QATest")))
                .andExpect(jsonPath("$.lastName", is("QASurname")));
>>>>>>> fbe799c4da015c97988314d0f6271c80e6bc18c9

    }

    @Test
<<<<<<< HEAD
    public void findRealEstateById_IdIsInvalid_ERRMessage() throws Exception {

        given(realEstateService.findRealEstateById(anyInt()))
                .willThrow(new InvalidRequestException("Sent Id is not valid."));

        mvc.perform(get("/invoice-realEstate/-123"))
=======
    public void findUserById_IdIsInvalid_ERRMessage() throws Exception {

        given(userService.findUserById(anyInt()))
                .willThrow(new InvalidRequestException("Sent Id is not valid."));

        mvc.perform(get("/invoice-user/-123"))
>>>>>>> fbe799c4da015c97988314d0f6271c80e6bc18c9
                .andExpect(status().isBadRequest());
    }

    @Test
<<<<<<< HEAD
    public void findRealEstateById_RealEstateIsNotFound_ERRMessage() throws Exception {

        given(realEstateService.findRealEstateById(anyInt()))
                .willThrow(new ItemNotFoundException("Object with sent ID is not found."));

        mvc.perform(get("/invoice-realEstate/100"))
=======
    public void findUserById_UserIsNotFound_ERRMessage() throws Exception {

        given(userService.findUserById(anyInt()))
                .willThrow(new ItemNotFoundException("Object with sent ID is not found."));

        mvc.perform(get("/invoice-user/100"))
>>>>>>> fbe799c4da015c97988314d0f6271c80e6bc18c9
                .andExpect(status().isNotFound());
    }

    @Test
<<<<<<< HEAD
    public void addNewRealEstate_StatusOkWithResult() throws Exception {
        List<Invoice> inv = new ArrayList<> ();

        RealEstate newRealEstate = new RealEstate("Grbavica", 100.00, inv);
        newRealEstate.setRealEstateId(1);

        given(realEstateService.saveRealEstate(newRealEstate)).willReturn(new ResponseEntity<>(newRealEstate, HttpStatus.OK));

        mvc.perform(post("/invoice-realEstate/add")
                .content(new ObjectMapper().writeValueAsString(newRealEstate))
=======
    public void addNewUser_StatusOkWithResult() throws Exception {
        List<Invoice> inv = new ArrayList<> ();

        User newUser = new User("QA", "Surname", inv);
        newUser.setUserId(1);

        given(userService.saveUser(newUser)).willReturn(new ResponseEntity<>(newUser, HttpStatus.OK));

        mvc.perform(post("/invoice-user/add")
                .content(new ObjectMapper().writeValueAsString(newUser))
>>>>>>> fbe799c4da015c97988314d0f6271c80e6bc18c9
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addNewUser_RequestIsInvalid_ERRMessage() throws Exception {
        List<Invoice> inv = new ArrayList<> ();
<<<<<<< HEAD
        RealEstate newRealEstate = new RealEstate("QA 392!#)$=", -100.00, inv);
        newRealEstate.setRealEstateId(1);

        given(realEstateService.saveRealEstate(newRealEstate))
                .willThrow(new InvalidRequestException("Wrong format of properties: Name, Price."));

        mvc.perform(post("/invice-realEstate/add")
                .content(new ObjectMapper().writeValueAsString(newRealEstate))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
=======
        User newUser = new User("QA 392!#)$=", "Ex 3z2 093", inv);

        given(userService.saveUser(ArgumentMatchers.<User>any()))
                .willThrow(new InvalidRequestException("Wrong format of properties: First Name, Last Name."));

        mvc.perform(post("/invice-user/add")
                .content(new ObjectMapper().writeValueAsString(newUser))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }*/
>>>>>>> fbe799c4da015c97988314d0f6271c80e6bc18c9
}
