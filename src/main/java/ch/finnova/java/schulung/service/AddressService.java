package ch.finnova.java.schulung.service;

import ch.finnova.java.schulung.db.model.Address;
import ch.finnova.java.schulung.db.repos.AddressRepository;
import ch.finnova.java.schulung.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final String NO_ADDRESS_TXT = "No address with id ";
    private final String AVAILABLE_TXT = " available";

    private final AddressRepository repository;

    public AddressService(final AddressRepository anAddressRepository) {
        repository = anAddressRepository;
    }

    public List<Address> findAll() {
        return repository.findAll();
    }

    public Long create(final Address user) {
        return repository.save(user).getId();
    }

    public Address findById(Long anId) {
        return repository.findById(anId).orElseThrow(() -> new NotFoundException(NO_ADDRESS_TXT + anId + AVAILABLE_TXT));
    }

    public void update(final Long anId, final Address aAddress) {
        final Address existingUser = repository.findById(anId)
                .orElseThrow(() -> new NotFoundException(NO_ADDRESS_TXT + anId + AVAILABLE_TXT));

        repository.save(aAddress);
    }

    public void delete(final Long id) {
        repository.deleteById(id);
    }
}
