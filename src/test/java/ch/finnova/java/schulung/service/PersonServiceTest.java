package ch.finnova.java.schulung.service;

import ch.finnova.java.schulung.db.model.Person;
import ch.finnova.java.schulung.db.repos.PersonRepository;
import ch.finnova.java.schulung.exceptions.NotFoundException;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Test
    void findAllShouldWorkWithPeople() {
        // given
        Person person = new Person();
        List<Person> people = new ArrayList<>();
        people.add(person);

        // when
        when(personRepository.findAll()).thenReturn(people);

        final List<Person> result = personService.findAll();

        // then
        assertFalse(result.isEmpty(), "There must be someone about!");
    }

    @Test
    void findAllMustFailWithNoPeople() {
        // given

        // when
        when(personRepository.findAll()).thenReturn(new ArrayList<Person>());

        final List<Person> result = personService.findAll();

        // then
        assertTrue(result.size() == 0, "There are result about! Zombies?!?");
    }

    @Test
    void create() {
    }

    @Test
    void findByIdMustFailForInvalidId() {
        // given
        Long id = 4711L;

        // when
        when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        // then
        final NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            personService.findById(id);
        });

        assertEquals("No person with id " + id + " available", exception.getMessage());
    }

    @Test
    void findByIdShouldWorkForValidId() {
        // given
        Long id = 4711L;

        Person person = new Person();
        person.setId(id);
        List<Person> people = new ArrayList<>();
        people.add(person);

        Optional<Person> p = Optional.of(person);

        // when
        when(personRepository.findById(id)).thenReturn(p);

        // then
        final Person result = personService.findById(id);
        assertEquals(person.getId(), result.getId(), "Must find correct person");

        final NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            personService.findById(id + 1);
        });

        assertEquals("No person with id " + (id + 1) + " available", exception.getMessage());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
