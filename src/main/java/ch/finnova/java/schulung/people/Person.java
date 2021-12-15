package ch.finnova.java.schulung.people;

public abstract class Person {
    private String name;

    public String getName() {
        return name;
    }

    public void greet(Person aPerson) {
        sayHello(aPerson.getName());
    }

    protected abstract void sayHello(String aName);
}
