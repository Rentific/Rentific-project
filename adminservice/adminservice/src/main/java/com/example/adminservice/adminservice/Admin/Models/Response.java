package com.example.adminservice.adminservice.Admin.Models;

import java.util.List;

public class Response {
    private List<RealEstate> realEstateList;
    private Integer totalPages;
    private Integer currentPage;
    private Integer totalItems;

    public Response(List<RealEstate> realEstateList, Integer totalPages, Integer currentPage, Integer totalItems) {
        this.realEstateList = realEstateList;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
    }

    public List<RealEstate> getRealEstateList() {
        return realEstateList;
    }

    public void setRealEstateList(List<RealEstate> realEstateList) {
        this.realEstateList = realEstateList;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }
}
