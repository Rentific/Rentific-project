package com.example.invoiceservice.invoiceservice.controllers;


import com.example.invoiceservice.invoiceservice.DTOs.RealEstateDTO;
import com.example.invoiceservice.invoiceservice.DTOs.RequestCreateInvoiceDTO;
import com.example.invoiceservice.invoiceservice.DTOs.UserDTO;
import com.example.invoiceservice.invoiceservice.ExceptionHandler.InvalidRequestException;
import com.example.invoiceservice.invoiceservice.ExceptionHandler.ItemNotFoundException;
import com.example.invoiceservice.invoiceservice.models.Invoice;
import com.example.invoiceservice.invoiceservice.models.RealEstate;
import com.example.invoiceservice.invoiceservice.models.User;
import com.example.invoiceservice.invoiceservice.services.InvoiceService;
import com.example.invoiceservice.invoiceservice.services.RealEstateService;
import com.example.invoiceservice.invoiceservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;



@RestController // This means that this class is a Controller
@RequestMapping(path="/invoice") // This means URL's start with /invoice (after Application path)
public class InvoiceController {
    private InvoiceService _invoiceService;
    private UserService _userService;
    private RealEstateService _realEstateService;

   @Autowired
    private RestTemplate restTemplate;


    public InvoiceController(InvoiceService invoiceService, UserService userService, RealEstateService realEstateService) {
        this._invoiceService = invoiceService;
        this._userService = userService;
        this._realEstateService = realEstateService;
    }

    @PostMapping(path="/add")
    ResponseEntity<Invoice> addNewInvoice (@RequestBody RequestCreateInvoiceDTO invToCreate) throws InvalidRequestException, ItemNotFoundException {

        //verifications for user
        UserDTO u = restTemplate.getForObject("http://user-service/user/" + invToCreate.getUserId(), UserDTO.class);
        User newUser1 = new User(u.getFirstName(), u.getLastName(), u.getEmail());
        User userForInvoice;
        try {
            userForInvoice = _userService.findUserByEmail(newUser1.getEmail()).getBody();
        }
        catch(ItemNotFoundException ex){
            userForInvoice = _userService.saveUser(newUser1).getBody();
        }

        //verifications for RealEstate
        RealEstateDTO r = restTemplate.getForObject("http://admin-service/real-estate/" + invToCreate.getRealEstateId(), RealEstateDTO.class);
        RealEstate newRealEstate = new RealEstate(r.getName(), r.getPrice());
        RealEstate realEstateForInvoice;
        try {
            realEstateForInvoice = _realEstateService.findRealEstateByRealEstateName(newRealEstate.getName()).getBody();
        }
        catch(ItemNotFoundException ex){
            realEstateForInvoice = _realEstateService.saveRealEstate(newRealEstate).getBody();
        }


        Invoice invToSave = new Invoice(new Date(), realEstateForInvoice, userForInvoice);
        return _invoiceService.saveInvoice(invToSave);

    }

    @GetMapping(path="/all")
    ResponseEntity<List<Invoice>>  getAllInvoices() {
        // This returns a JSON or XML with the all invoices
        return _invoiceService.findAllInvoices();
    }

    @GetMapping
    @RequestMapping("{id}")
    ResponseEntity<Invoice>  getInvoiceById(@PathVariable Integer id) throws InvalidRequestException, ItemNotFoundException {
        // This returns a JSON or XML with the all invoices
        return _invoiceService.findInvoicesById(id);
    }

    @GetMapping
    @RequestMapping("/user/{id}")
    ResponseEntity<List<Invoice>> getAllInvoicesForSpecificUser(@PathVariable Integer id) throws InvalidRequestException, ItemNotFoundException{
        return _invoiceService.FindAllInvoicesForSpecificUser(id);
    }

    @GetMapping(
            value = "/user",
            params = { "firstName", "lastName" })
    @ResponseBody
    ResponseEntity<List<Invoice>> getAllInvoicesForSpecificUserNameSurname(@RequestParam("firstName") String name, @RequestParam("lastName") String surname) throws InvalidRequestException, ItemNotFoundException{
        return _invoiceService.FindAllInvoicesForSpecificUserNameSurname(name, surname);
    }

    @GetMapping
    @RequestMapping(value = "/realEstate/{id}")
    ResponseEntity<List<Invoice>> getAllInvoicesForRealEstate(@PathVariable Integer id) throws InvalidRequestException, ItemNotFoundException{
        return _invoiceService.FindAllInvoicesForSpecificRealEstate(id);
    }


}