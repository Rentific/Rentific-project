package com.example.adminservice.adminservice.Admin.Wrappers;

import com.example.adminservice.adminservice.Admin.Dtos.ReservedRealEstate;

import java.util.List;

public class RealEstateResponseWrapper {
    List<ReservedRealEstate> realEstate2List;

    public RealEstateResponseWrapper() {
    }

    public RealEstateResponseWrapper(List<ReservedRealEstate> realEstate2List) {
        this.realEstate2List = realEstate2List;
    }

    public List<ReservedRealEstate> getRealEstate2List() {
        return realEstate2List;
    }

    public void setRealEstate2List(List<ReservedRealEstate> realEstate2List) {
        this.realEstate2List = realEstate2List;
    }
}
