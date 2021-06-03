package com.example.adminservice.adminservice.Admin.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "image_model")
public class ImageModel {

    public ImageModel() {
        super();
    }

    public ImageModel(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

    public ImageModel(String name, String type, byte[] picByte, RealEstate realEstate) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
        this.realEstate = realEstate;
    }

    public ImageModel(Long imageId, String name, String type, byte[] picByte, RealEstate realEstate) {
        this.imageId = imageId;
        this.name = name;
        this.type = type;
        this.picByte = picByte;
        this.realEstate = realEstate;
    }

    public ImageModel(Long imageId, String name, String type, byte[] picByte) {
        this.imageId = imageId;
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

    @Id
    @Column(name = "imageId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Lob
    private byte[] picByte;

    @JsonBackReference(value="name")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "realEstateId")
    private RealEstate realEstate;

    public Long getId() {
        return imageId;
    }

    public void setId(Long id) {
        this.imageId = id;
    }

    public RealEstate getRealEstate() {
        return realEstate;
    }

    public void setRealEstate(RealEstate realEstate) {
        this.realEstate = realEstate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }
}
