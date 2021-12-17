package ch.finnova.java.schulung.generics;

import ch.finnova.java.schulung.people.Person;

public class SuperTyp<T extends Person> {
    private T personTyp;

    public SuperTyp(T type) {
        personTyp = type;
    }
    public T getPersonTyp() {
        return personTyp;
    }
}
