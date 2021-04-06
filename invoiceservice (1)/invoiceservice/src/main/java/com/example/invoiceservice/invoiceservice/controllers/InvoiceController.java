package com.example.invoiceservice.invoiceservice.controllers;

import com.example.invoiceservice.invoiceservice.ExceptionHandler.InvalidRequestException;
import com.example.invoiceservice.invoiceservice.ExceptionHandler.ItemNotFoundException;
import com.example.invoiceservice.invoiceservice.models.Invoice;
import com.example.invoiceservice.invoiceservice.models.User;
import com.example.invoiceservice.invoiceservice.services.InvoiceService;
import com.example.invoiceservice.invoiceservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController // This means that this class is a Controller
@RequestMapping(path="/invoice") // This means URL's start with /invoice (after Application path)
public class InvoiceController {

    private InvoiceService _invoiceService;

    public InvoiceController(InvoiceService invoiceService, UserService userService) {
        this._invoiceService = invoiceService;
    }

    @PostMapping(path="/add") // Map ONLY POST Requests
    ResponseEntity<Invoice> addNewInvoice (@RequestBody Invoice invoice) throws InvalidRequestException {
        return _invoiceService.saveInvoice(invoice);
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