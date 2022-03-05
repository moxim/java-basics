package ch.finnova.java.schulung.db.repos;

import ch.finnova.java.schulung.db.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
