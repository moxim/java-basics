package ch.finnova.java.schulung.people;

public class Person {
    private final String name;

    public void setName(String newName) {
        name = newName; // this won't work
    }

    public Person(String newName) {
        name = newName;
    }
}
