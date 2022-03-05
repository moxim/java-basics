package ch.finnova.java.schulung.db.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.Set;

public class Address {
    private Long id;

    private String street;

    private String number;

    private String plz;

    private String city;

    @JsonManagedReference
    private Set<Person> inhabitants = new HashSet<>();

    public Address() {
        // empty c'tor required by JPA
    }

    public Long getId() {
        return id;
    }

    public void setId(Long aId) {
        id = aId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String aStreet) {
        street = aStreet;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String aNumber) {
        number = aNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String aCity) {
        city = aCity;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String aPlz) {
        plz = aPlz;
    }

    public Set<Person> getInhabitants() {
        return new HashSet<>(inhabitants);
    }

    public void remove(Person aPerson) {
        inhabitants.remove(aPerson);
    }

    public void add(Person aPerson) {
        inhabitants.add(aPerson);
    }

    @Override
    public String toString() {
        String result = "Address{id=" + id + ", street=" + street + ", number=" + number + ", plz=" + plz + ", city=" +
                city + ", inhabitants=" + inhabitants + "}";
        return result;
    }

    @Override
    public boolean equals(Object aO) {
        if (this == aO) return true;
        if (aO == null || getClass() != aO.getClass()) return false;

        Address address = (Address) aO;

        return id.equals(address.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
