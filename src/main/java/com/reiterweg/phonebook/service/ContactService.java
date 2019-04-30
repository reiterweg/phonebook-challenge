package com.reiterweg.phonebook.service;

import com.reiterweg.phonebook.domain.Contact;
import com.reiterweg.phonebook.repository.ContactRepository;
import com.reiterweg.phonebook.service.dto.ContactDTO;
import com.reiterweg.phonebook.service.mapper.ContactMapper;
import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactService {

  private final ContactRepository contactRepository;
  private final ContactMapper contactMapper;

  public ContactService(ContactRepository contactRepository, ContactMapper contactMapper) {
    this.contactRepository = contactRepository;
    this.contactMapper = contactMapper;
  }

  public ContactDTO save(ContactDTO contactDTO) {
    Contact contact = contactMapper.toEntity(contactDTO);
    contact = contactRepository.save(contact);
    return contactMapper.toDTO(contact);
  }

  @Transactional(readOnly = true)
  public List<ContactDTO> find(String searchFilter) {
    Contact contact = new Contact();
    contact.setFirstName(searchFilter);
    contact.setLastName(searchFilter);
    contact.setPhone(searchFilter);

    ExampleMatcher exampleMatcher =
        ExampleMatcher.matchingAny()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
            .withIgnoreCase();
    Example<Contact> example = Example.of(contact, exampleMatcher);

    return contactMapper.toDTO(contactRepository.findAll(example));
  }
}
