package ch.finnova.java.schulung.db.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "People")
public class Person {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    @PrePersist
    public void prePersist() {
        dateCreated = OffsetDateTime.now();
        lastUpdated = dateCreated;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdated = OffsetDateTime.now();
    }

    public Person() {
        // empty c'tor required by JPA
    }

    public Person(String aName) {
        name = aName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long aId) {
        id = aId;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
    }

    public Address getAddress() {
        return address;
    }

    public void moveTo(Address anAddress) {
        if (address != null) {
            address.remove(this);
        }
        address = anAddress;
        address.add(this);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object aO) {
        if (this == aO) return true;
        if (aO == null || getClass() != aO.getClass()) return false;

        Person person = (Person) aO;

        return id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 1 : id.hashCode();
    }
}
