package com.reiterweg.phonebook.web.rest;

import static com.reiterweg.phonebook.web.ResourceConstants.RESOURCE_CONTACT_MAPPING;

import com.reiterweg.phonebook.service.ContactService;
import com.reiterweg.phonebook.service.dto.ContactDTO;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RESOURCE_CONTACT_MAPPING)
public class ContactResource {

  private final ContactService contactService;

  public ContactResource(ContactService contactService) {
    this.contactService = contactService;
  }

  @PostMapping
  public ResponseEntity<ContactDTO> createContact(@Valid @RequestBody ContactDTO contactDTO)
      throws Exception {
    if (contactDTO.getId() != null) {
      throw new Exception();
    }
    ContactDTO result = contactService.save(contactDTO);
    return ResponseEntity.created(new URI(RESOURCE_CONTACT_MAPPING + result.getId())).body(result);
  }

  @GetMapping
  public ResponseEntity<List<ContactDTO>> getContacts(
      @Valid @RequestParam(required = false) String searchFilter) {
    List<ContactDTO> contacts = contactService.find(searchFilter);
    return ResponseEntity.ok().body(contacts);
  }
}
