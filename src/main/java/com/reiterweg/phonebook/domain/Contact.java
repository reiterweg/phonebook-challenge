package com.reiterweg.phonebook.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "contacts")
public class Contact implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GenericGenerator(
      name = "contactGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "contacts_id_seq"),
        @Parameter(name = "initial_value", value = "1"),
        @Parameter(name = "increment_size", value = "1")
      })
  @GeneratedValue(generator = "contactGenerator")
  private Long id;

  @NotNull
  @Size(max = 80)
  @Column(name = "first_name", length = 80, nullable = false)
  private String firstName;

  @NotNull
  @Size(max = 80)
  @Column(name = "last_name", length = 80, nullable = false)
  private String lastName;

  @NotNull
  @Size(max = 80)
  @Column(name = "phone", length = 80, nullable = false)
  private String phone;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Contact contact = (Contact) o;
    if (contact.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), contact.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }
}
