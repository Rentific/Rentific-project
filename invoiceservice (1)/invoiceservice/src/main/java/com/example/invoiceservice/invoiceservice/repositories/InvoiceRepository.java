package com.example.invoiceservice.invoiceservice.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.invoiceservice.invoiceservice.models.Invoice;

public interface InvoiceRepository  extends JpaRepository<Invoice, Integer> {
}


