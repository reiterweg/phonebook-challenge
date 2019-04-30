package com.reiterweg.phonebook.service;

import static com.reiterweg.phonebook.fixture.ContactFixture.DEFAULT_FIRST_NAME;
import static com.reiterweg.phonebook.fixture.ContactFixture.DEFAULT_LAST_NAME;
import static com.reiterweg.phonebook.fixture.ContactFixture.DEFAULT_PHONE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.reiterweg.phonebook.fixture.ContactFixture;
import com.reiterweg.phonebook.repository.ContactRepository;
import com.reiterweg.phonebook.service.dto.ContactDTO;
import com.reiterweg.phonebook.service.mapper.ContactMapper;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ContactService.class, ContactServiceTest.class})
public class ContactServiceTest {

  @Bean
  public ContactRepository contactRepository() {
    return mock(ContactRepository.class);
  }

  @Bean
  public ContactMapper contactMapper() {
    return mock(ContactMapper.class);
  }

  @Mock private ContactRepository contactRepository;

  @Mock private ContactMapper contactMapper;

  private ContactService contactService;

  @Before
  public void setup() {
    initMocks(this);
    contactService = new ContactService(contactRepository, contactMapper);
  }

  @Test
  public void shouldSaveContact() {
    when(contactService.save(any())).thenReturn(ContactFixture.contactWithId());

    ContactDTO saved = contactService.save(ContactFixture.contactWithoutId());

    assertThat(saved.getId(), notNullValue());
    assertThat(saved.getId(), greaterThan(0L));
    assertThat(saved.getFirstName(), equalTo(DEFAULT_FIRST_NAME));
    assertThat(saved.getLastName(), equalTo(DEFAULT_LAST_NAME));
    assertThat(saved.getPhone(), equalTo(DEFAULT_PHONE));
  }

  @Test
  public void shouldFindContacts() {
    when(contactService.save(any())).thenReturn(ContactFixture.contactWithId());

    contactService.save(ContactFixture.contactWithoutId());

    when(contactService.find(anyString())).thenReturn(ContactFixture.contactsWithId());

    List<ContactDTO> contacts = contactService.find(DEFAULT_FIRST_NAME);
    ContactDTO retrieved = contacts.get(contacts.size() - 1);

    assertThat(retrieved.getFirstName(), equalTo(DEFAULT_FIRST_NAME));
    assertThat(retrieved.getLastName(), equalTo(DEFAULT_LAST_NAME));
    assertThat(retrieved.getPhone(), equalTo(DEFAULT_PHONE));
  }
}
