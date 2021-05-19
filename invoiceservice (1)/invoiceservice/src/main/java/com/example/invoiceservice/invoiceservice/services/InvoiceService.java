package com.example.invoiceservice.invoiceservice.services;

import com.example.invoiceservice.invoiceservice.ExceptionHandler.InvalidRequestException;
import com.example.invoiceservice.invoiceservice.ExceptionHandler.ItemNotFoundException;
import com.example.invoiceservice.invoiceservice.models.Invoice;
import com.example.invoiceservice.invoiceservice.models.RealEstate;
import com.example.invoiceservice.invoiceservice.repositories.InvoiceRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    private final InvoiceRepository _invoiceRepository;
    private final ValidationService _validationService;

    public InvoiceService(InvoiceRepository invoiceRepository, ValidationService _validationService) {
        this._invoiceRepository = invoiceRepository;
        this._validationService = _validationService;
    }

    public ResponseEntity<List<Invoice>> findAllInvoices() {
        return new ResponseEntity<List<Invoice>>(this._invoiceRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Invoice> findInvoicesById(Integer id) throws InvalidRequestException, ItemNotFoundException {
        this._validationService.validateId(id);

        Optional<Invoice> invoice = this._invoiceRepository.findById(id);
        this._validationService.validateItem(invoice);
        return new ResponseEntity(invoice.get(), HttpStatus.OK);
    }

    public ResponseEntity<Invoice> saveInvoice(Invoice invoice) throws InvalidRequestException {
        //this._validationService.validateInvoiceProperties(invoice);
        Invoice newInvoice = this._invoiceRepository.save(invoice);
        return new ResponseEntity(newInvoice, HttpStatus.OK);
    }


    public ResponseEntity<List<Invoice>> FindAllInvoicesForSpecificUser(Integer id) throws InvalidRequestException, ItemNotFoundException{
        this._validationService.validateId(id);
        List<Invoice> invoiceLists = this._invoiceRepository.FindAllInvoicesForSpecificUser(id);
        return new ResponseEntity(invoiceLists, HttpStatus.OK);
    }
    public ResponseEntity<List<Invoice>> FindAllInvoicesForSpecificUserNameSurname(String name, String surname) throws InvalidRequestException, ItemNotFoundException{
        List<Invoice> invoiceLists = this._invoiceRepository.FindAllInvoicesForSpecificUserNameSurname(name,surname);
        return new ResponseEntity(invoiceLists, HttpStatus.OK);
    }


    public ResponseEntity<List<Invoice>> FindAllInvoicesForSpecificRealEstate(Integer id) throws InvalidRequestException, ItemNotFoundException{
        this._validationService.validateId(id);
        List<Invoice> invoiceLists = this._invoiceRepository.FindAllInvoicesForSpecificRealEstate(id);
        return new ResponseEntity(invoiceLists, HttpStatus.OK);
    }

    public ResponseEntity DeleteInvoiceById(Integer id) throws InvalidRequestException, ItemNotFoundException{
        this._validationService.validateId(id);
        findInvoicesById(id);
        this._invoiceRepository.deleteById(id);
        return new ResponseEntity("Invoice successfully deleted.", HttpStatus.OK);
    }

}

