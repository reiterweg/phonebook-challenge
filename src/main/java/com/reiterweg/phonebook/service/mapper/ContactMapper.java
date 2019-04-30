package com.reiterweg.phonebook.service.mapper;

import com.reiterweg.phonebook.domain.Contact;
import com.reiterweg.phonebook.service.dto.ContactDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {

  Contact toEntity(ContactDTO dto);

  ContactDTO toDTO(Contact entity);

  List<Contact> toEntity(List<ContactDTO> dtos);

  List<ContactDTO> toDTO(List<Contact> entities);
}
