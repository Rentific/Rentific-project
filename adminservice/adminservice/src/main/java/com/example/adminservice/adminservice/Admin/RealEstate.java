package com.example.adminservice.adminservice.Admin;

import javax.persistence.*;

@Entity
public class RealEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ORACLE_DB_SEQ_ID")
     @Column(name = "real_estate_id")
    private Long realEstateId;
    private String name;
    private Integer price;

    @OneToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "state_id", referencedColumnName = "real_estate_id")
    private State stateId;
    private String address;
    private String country;
    private String city;
    private String description;
    private Boolean isReservated;
    private Boolean isRented;
    private Integer userId;

    public RealEstate() {
    }

    public RealEstate(
            String name,
            Integer price,
            State stateId,
            String address,
            String country,
            String city,
            String description,
            Boolean isReservated,
            Boolean isRented,
            Integer userId) {
        this.name = name;
        this.price = price;
        this.stateId = stateId;
        this.address = address;
        this.country = country;
        this.city = city;
        this.description = description;
        this.isReservated = isReservated;
        this.isRented = isRented;
        this.userId = userId;
    }

   /* @Id
    public Long getRealEstateId() {
        return realEstateId;
    }*/

    /*public void setRealEstateId(Long realEstateId) {
        this.realEstateId = realEstateId;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public State getStateId() {
        return stateId;
    }

    public void setStateId(State stateId) {
        this.stateId = stateId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsReservated() {
        return isReservated;
    }

    public void setIsReservated(Boolean reservated) {
        isReservated = reservated;
    }

    public Boolean getIsRented() {
        return isRented;
    }

    public void setIsRented(Boolean rented) {
        isRented = rented;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
