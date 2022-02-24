package com.geekbrains.spring.web.api.core;

import java.util.List;
import java.math.BigDecimal;

public class OrderDetailsDto {

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getHouse() {
    return house;
  }

  public void setHouse(String house) {
    this.house = house;
  }

  public String getFlat() {
    return flat;
  }

  public void setFlat(String flat) {
    this.flat = flat;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public OrderDetailsDto(String username, String name, String surname, String street, String house, String flat, String city, String district, String postalCode, String countryCode, String phone) {
    this.username = username;
    this.name = name;
    this.surname = surname;
    this.street = street;
    this.house = house;
    this.flat = flat;
    this.city = city;
    this.district = district;
    this.postalCode = postalCode;
    this.countryCode = countryCode;
    this.phone = phone;
  }

  private String username;
  private String name;
  private String surname;
  private String street;
  private String house;
  private String flat;
  private String city;
  private String district;
  private String postalCode;
  private String countryCode;
  private String phone;




    public OrderDetailsDto() {
    }

}
