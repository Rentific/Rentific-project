package com.example.adminservice.adminservice.Admin.Dtos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;


/*@Entity*/ // This tells Hibernate to make a table out of this class
public class User {
   /* @JsonProperty("userId")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)*/
    private Integer userId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email(message = "Email should be valid")
    private String email;
    @Past
    private Date dateOfBirth;
    private String phone;

   /* @JsonManagedReference(value="name")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<RealEstate2> realEstates;*/

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

  /*  public List<RealEstate2> getRealEstates() {
        return realEstates;
    }

    public void setRealEstates(List<RealEstate2> realEstates) {
        this.realEstates = realEstates;
    }*/
    public User() {
    }

    public User(User user){
        this.setUserId(user.userId);
        this.setPhone(user.phone);
        this.setEmail(user.email);
        this.setFirstName(user.firstName);
        this.setLastName(user.lastName);
        this.setDateOfBirth(user.dateOfBirth);
      /*  this.setRealEstates(user.realEstates);*/
    }

    public User(String firstName, String lastName, String email, Date dateOfBirth, String phone, List<RealEstate2> realEstates) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        /*this.realEstates = realEstates;*/
    }

}