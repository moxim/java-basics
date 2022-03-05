package ch.finnova.java.schulung.db.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class Person {
    private Long id;

    private String name;

    @JsonBackReference
    private Address address;

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
