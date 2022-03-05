package ch.finnova.java.schulung.db.repos;

import ch.finnova.java.schulung.db.model.Address;
import ch.finnova.java.schulung.db.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void findNonExistingPerson() {
        final Optional<Person> person = personRepository.findById(14L);
        assertTrue(person.isEmpty(), "This person can't be present!");
    }

    @Test
    public void findExistingPerson() throws Exception {
        // given
        setupTestData();

        // when
        final List<Person> people = personRepository.findAll();

        // then
        assertEquals(1, people.size(), "There must be exactly one person present!");
    }

    @Test
    public void findPersonThroughAddress() throws Exception {
        // given
        setupTestData();

        // when
        final List<Address> addresses = addressRepository.findAll();

        // then
        assertEquals(1, addresses.size(), "There must be exactly one address present!");
        assertEquals(1, addresses.get(0).getInhabitants().size());
    }

    @Test
    public void findAdam() throws Exception {
        // given
        setupTestData();

        // when
        final Person adam = personRepository.findAdamUsingSQL();

        // then
        assertNotNull(adam, "Come on, there must have been an adam!");
    }

    private void setupTestData() {
        final Address home = new Address();
        home.setPlz("5600");
        addressRepository.save(home);

        final Person person = new Person();
        person.setName("Duke");

        person.moveTo(home);
        personRepository.save(person);
    }
}
