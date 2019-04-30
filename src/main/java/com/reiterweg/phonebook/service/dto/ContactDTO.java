package com.reiterweg.phonebook.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ContactDTO {

  private Long id;

  @NotNull
  @Size(max = 80)
  private String firstName;

  @NotNull
  @Size(max = 80)
  private String lastName;

  @NotNull
  @Size(max = 80)
  private String phone;

  public ContactDTO() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
