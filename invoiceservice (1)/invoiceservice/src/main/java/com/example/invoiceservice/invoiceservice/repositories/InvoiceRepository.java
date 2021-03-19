package com.example.invoiceservice.invoiceservice.repositories;
import org.springframework.data.repository.CrudRepository;

import com.example.invoiceservice.invoiceservice.models.Invoice;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface InvoiceRepository  extends CrudRepository<Invoice, Integer> {
}


