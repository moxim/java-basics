package ch.finnova.java.schulung.db.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Address {
    @Id
    @Column(nullable = false, updatable = false, name = "address_id")
    @GeneratedValue
    private Long id;

    private String street;

    private String number;

    @Column(nullable = false)
    private String plz;

    private String city;

    @JsonManagedReference
    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    private Set<Person> inhabitants = new HashSet<>();

    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Address() {
        // empty c'tor required by JPA
    }

    @PrePersist
    public void prePersist() {
        dateCreated = OffsetDateTime.now();
        lastUpdated = dateCreated;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdated = OffsetDateTime.now();
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
