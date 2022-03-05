package ch.finnova.java.schulung.rest;

import ch.finnova.java.schulung.db.model.Address;
import ch.finnova.java.schulung.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class AddressController {

  private final AddressService addressService;

  AddressController(AddressService aAddressService) {
    addressService = aAddressService;}

  @GetMapping("/addresses")
  List<Address> getAllAddresses() {
    return addressService.findAll();
  }

  @PostMapping("/addresses")
  Long createNewAddress(@RequestBody Address newAddress) {
    return addressService.create(newAddress);
  }

  // Single item
  @GetMapping("/addresses/{id}")
  Address getSpecificAddress(@PathVariable Long id) {
    return addressService.findById(id);
  }

  /*
  @PutMapping("/addresses/{id}")
  Address replaceAddress(@RequestBody Address aNewAddress, @PathVariable Long id) {
    return repository.findById(id)
      .map(Address -> {
        Address.setName(aNewAddress.getName());
        return repository.save(Address);
      })
      .orElseGet(() -> {
        aNewAddress.setId(id);
        return repository.save(aNewAddress);
      });
  }

  @DeleteMapping("/addresses/{id}")
  void deleteAddress(@PathVariable Long id) {
    repository.deleteById(id);
  }
   */
}
