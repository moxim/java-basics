package ch.finnova.java.schulung.service;

import ch.finnova.java.schulung.db.model.Person;
import ch.finnova.java.schulung.db.repos.AddressRepository;
import ch.finnova.java.schulung.db.repos.PersonRepository;
import ch.finnova.java.schulung.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final String NO_PERSON_TXT = "No person with id ";
    private final String AVAILABLE_TXT = " available";

    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    public PersonService(final PersonRepository aPersonRepository, AddressRepository anAddressRepository) {
        personRepository = aPersonRepository;
        addressRepository = anAddressRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Long create(final Person user) {
        addressRepository.save(user.getAddress());
        return personRepository.save(user).getId();
    }

    public Person findById(Long anId) {
        return personRepository.findById(anId).orElseThrow(() -> new NotFoundException(NO_PERSON_TXT + anId + AVAILABLE_TXT));
    }

    public void update(final Long anId, final Person aPerson) {
        final Person existingUser = personRepository.findById(anId)
                .orElseThrow(() -> new NotFoundException(NO_PERSON_TXT + anId + AVAILABLE_TXT));

        personRepository.save(aPerson);
    }

    public void delete(final Long id) {
        personRepository.deleteById(id);
    }
}
