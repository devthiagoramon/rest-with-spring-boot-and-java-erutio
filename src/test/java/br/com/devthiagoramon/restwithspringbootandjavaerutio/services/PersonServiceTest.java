package br.com.devthiagoramon.restwithspringbootandjavaerutio.services;

import br.com.devthiagoramon.restwithspringbootandjavaerutio.data.vo.v1.PersonDTO;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.exceptions.RequestObjectNullException;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.model.Person;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.repository.PersonRepository;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.unittests.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    @BeforeEach
    void setUpMocks() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<Person> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);

        var persons = service.findAll();

        assertNotNull(persons);
        assertEquals(14, persons.size());
        var person1 = persons.get(1);
        assertNotNull(person1);
        assertNotNull(person1.getKey());
        assertNotNull(person1.getLinks());
        assertTrue(person1.toString()
                         .contains("links: [</api/person/v1/1>;rel=\"self\"]"));

        var person4 = persons.get(4);
        assertNotNull(person4);
        assertNotNull(person4.getKey());
        assertNotNull(person4.getLinks());
        assertTrue(person4.toString()
                         .contains("links: [</api/person/v1/4>;rel=\"self\"]"));
        var person7 = persons.get(7);
        assertNotNull(person7);
        assertNotNull(person7.getKey());
        assertNotNull(person7.getLinks());
        assertTrue(person7.toString()
                         .contains("links: [</api/person/v1/7>;rel=\"self\"]"));
    }

    @Test
    void findById() {
        Person person = input.mockEntity();
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));
        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString()
                         .contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals(person.getAddress(), result.getAddress());
        assertEquals(person.getFirstName(), result.getFirstName());
        assertEquals(person.getLastName(), result.getLastName());
        assertEquals(person.getGender(), result.getGender());
    }

    @Test
    void create() {
        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);
        dto.setKey(1L);

        when(repository.save(person)).thenReturn(persisted);
        var result = service.create(dto);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString()
                         .contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals(person.getAddress(), result.getAddress());
        assertEquals(person.getFirstName(), result.getFirstName());
        assertEquals(person.getLastName(), result.getLastName());
        assertEquals(person.getGender(), result.getGender());

    }

    @Test
    void testCreateWithNullPerson() {
        Exception e = assertThrows(RequestObjectNullException.class, () -> service.create(null));
        String excpetedMessage = "It's not allowed to persist a null object";
        String actualMessage = e.getMessage();
        assertTrue(actualMessage.contains(excpetedMessage));
    }

    @Test
    void update() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        PersonDTO dto = input.mockDTO(1);
        dto.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        when(repository.save(person)).thenReturn(person);

        var result = service.update(1L, dto);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString()
                         .contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals(person.getAddress(), result.getAddress());
        assertEquals(person.getFirstName(), result.getFirstName());
        assertEquals(person.getLastName(), result.getLastName());
        assertEquals(person.getGender(), result.getGender());

    }

    @Test
    void delete() {
        Person person = input.mockEntity();
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));
        service.delete(1L);
    }
}