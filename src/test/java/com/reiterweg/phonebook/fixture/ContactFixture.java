package com.reiterweg.phonebook.fixture;

import com.reiterweg.phonebook.service.dto.ContactDTO;
import java.util.Arrays;
import java.util.List;

public class ContactFixture {

  public static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
  public static final String DEFAULT_LAST_NAME = "BBBBBBBBBB";
  public static final String DEFAULT_PHONE = "9999999999";

  public static ContactDTO contactWithoutId() {
    ContactDTO contactDTO = new ContactDTO();
    contactDTO.setFirstName(DEFAULT_FIRST_NAME);
    contactDTO.setLastName(DEFAULT_LAST_NAME);
    contactDTO.setPhone(DEFAULT_PHONE);
    return contactDTO;
  }

  public static ContactDTO contactWithId() {
    ContactDTO contactDTO = new ContactDTO();
    contactDTO.setId(1L);
    contactDTO.setFirstName(DEFAULT_FIRST_NAME);
    contactDTO.setLastName(DEFAULT_LAST_NAME);
    contactDTO.setPhone(DEFAULT_PHONE);
    return contactDTO;
  }

  public static List<ContactDTO> contactsWithId() {
    return Arrays.asList(contactWithId());
  }
}
