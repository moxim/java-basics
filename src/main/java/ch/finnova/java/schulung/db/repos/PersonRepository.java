package ch.finnova.java.schulung.db.repos;

import ch.finnova.java.schulung.db.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * Find people of a name.
     *
     * @param aName the name to match
     * @return the people of a given name
     */
    Collection<Person> findPeopleByName(String aName);

    /**
     * Use SQL to find the first person.
     *
     * @return the first person
     */
    // id must be 2 since we store his address first
    @Query(value = "SELECT * FROM People x WHERE x.id = 2", nativeQuery = true)
    Person findAdamUsingSQL();

    /**
     * Use JPQL to find the first person.
     *
     * @return the first person
     */
    // id must be 2 since we store his address first
    @Query("SELECT x FROM Person x WHERE x.id = 2")
    Person findAdam();
}
