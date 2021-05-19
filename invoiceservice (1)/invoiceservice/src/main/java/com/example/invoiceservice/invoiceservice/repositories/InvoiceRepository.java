package com.example.invoiceservice.invoiceservice.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.invoiceservice.invoiceservice.models.Invoice;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepository  extends JpaRepository<Invoice, Integer> {

    @Query("SELECT inv FROM Invoice inv WHERE inv.user.userId = :id")
    List<Invoice> FindAllInvoicesForSpecificUser(@Param("id") Integer userId);

    @Query("SELECT inv FROM Invoice inv WHERE inv.user.firstName = :name and inv.user.lastName = :surname")
    List<Invoice> FindAllInvoicesForSpecificUserNameSurname(@Param("name") String fName, @Param("surname") String lName);

    @Query("SELECT inv FROM Invoice inv WHERE inv.realEstate.realEstateId = :id")
    Invoice FindInvoiceForSpecificRealEstate(@Param("id") Integer realEstateId);

}


