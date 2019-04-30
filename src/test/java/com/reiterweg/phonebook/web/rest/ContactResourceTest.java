package com.reiterweg.phonebook.web.rest;

import static com.reiterweg.phonebook.fixture.ContactFixture.DEFAULT_FIRST_NAME;
import static com.reiterweg.phonebook.fixture.ContactFixture.DEFAULT_LAST_NAME;
import static com.reiterweg.phonebook.fixture.ContactFixture.DEFAULT_PHONE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reiterweg.phonebook.fixture.ContactFixture;
import com.reiterweg.phonebook.service.ContactService;
import com.reiterweg.phonebook.service.dto.ContactDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ContactResource.class, ContactResourceTest.class})
public class ContactResourceTest {

  private static final String RESOURCE_URL = "/api/contacts";

  @Bean
  public ContactService contactService() {
    return mock(ContactService.class);
  }

  @Mock
  private ContactService contactService;

  private ObjectMapper objectMapper;
  private MockMvc mockMvc;

  @Before
  public void setup() {
    initMocks(this);
    objectMapper = new ObjectMapper();
    mockMvc = MockMvcBuilders.standaloneSetup(new ContactResource(contactService)).build();
  }

  @Test
  public void createContact() throws Exception {
    when(contactService.save(any())).thenReturn(ContactFixture.contactWithId());

    String contactJson = objectMapper.writeValueAsString(ContactFixture.contactWithoutId());
    mockMvc
        .perform(post(RESOURCE_URL).contentType(MediaType.APPLICATION_JSON).content(contactJson))
        .andExpect(status().isCreated());

    ArgumentCaptor<ContactDTO> argumentCaptor = ArgumentCaptor.forClass(ContactDTO.class);
    verify(contactService, times(1)).save(argumentCaptor.capture());

    ContactDTO sent = argumentCaptor.getValue();
    assertThat(sent.getFirstName(), equalTo(DEFAULT_FIRST_NAME));
    assertThat(sent.getLastName(), equalTo(DEFAULT_LAST_NAME));
    assertThat(sent.getPhone(), equalTo(DEFAULT_PHONE));
  }

  @Test
  public void findContacts() throws Exception {
    when(contactService.find(anyString())).thenReturn(ContactFixture.contactsWithId());

    mockMvc
        .perform(get(RESOURCE_URL).param("searchFilter", DEFAULT_FIRST_NAME))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
        .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
        .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)));

    ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
    verify(contactService, times(1)).find(argumentCaptor.capture());

    String sent = argumentCaptor.getValue();
    assertThat(sent, equalTo(DEFAULT_FIRST_NAME));
  }
}
