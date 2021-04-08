package com.example.adminservice.adminservice.Admin.Wrappers;

import com.example.adminservice.adminservice.Admin.Dtos.RealEstate2;

import java.util.List;

public class RealEstateResponseWrapper {
    List<RealEstate2> realEstate2List;

    public RealEstateResponseWrapper() {
    }

    public RealEstateResponseWrapper(List<RealEstate2> realEstate2List) {
        this.realEstate2List = realEstate2List;
    }

    public List<RealEstate2> getRealEstate2List() {
        return realEstate2List;
    }

    public void setRealEstate2List(List<RealEstate2> realEstate2List) {
        this.realEstate2List = realEstate2List;
    }
}
