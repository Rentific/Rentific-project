package com.example.invoiceservice.invoiceservice.DTOs;

import javax.validation.constraints.NotNull;
import java.util.List;

public class RequestCreateInvoiceDTO {
    @NotNull(message = "User can not be null")
    private Integer userId;
    private Integer realEstateId;

    public RequestCreateInvoiceDTO(@NotNull(message = "User can not be null") Integer userId, Integer realEstateId) {
        this.userId = userId;
        this.realEstateId = realEstateId;
    }

    public Integer getUserId() {
        return userId;
    }
    public Integer getRealEstateId() {
        return realEstateId;
    }
}
