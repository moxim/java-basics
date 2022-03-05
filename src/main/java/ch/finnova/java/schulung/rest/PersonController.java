package ch.finnova.java.schulung.rest;

import ch.finnova.java.schulung.db.model.Person;
import ch.finnova.java.schulung.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class PersonController {

    private final PersonService personService;

    PersonController(PersonService aPersonService) {
        personService = aPersonService;
    }

    @GetMapping("/people")
    List<Person> all() {
        return personService.findAll();
    }

    @PostMapping("/people")
    Long newPerson(@RequestBody Person newPerson) {
        return personService.create(newPerson);
    }

  /*
  // Single item
  @GetMapping("/people/{id}")
  Person one(@PathVariable Long id) {
    return repository.findById(id)
      .orElseThrow(() -> new NotFoundException("No persion with " + id + " present."));
  }

  @PutMapping("/people/{id}")
  Person replacePerson(@RequestBody Person aNewPerson, @PathVariable Long id) {
    return repository.findById(id)
      .map(Person -> {
        Person.setName(aNewPerson.getName());
        return repository.save(Person);
      })
      .orElseGet(() -> {
        aNewPerson.setId(id);
        return repository.save(aNewPerson);
      });
  }

  @DeleteMapping("/people/{id}")
  void deletePerson(@PathVariable Long id) {
    repository.deleteById(id);
  }
   */
}
