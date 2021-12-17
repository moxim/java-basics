package ch.finnova.java.schulung.people;

public class Person {
    private String name;

    public void doSomethingCool() {
        this.sayHello();
    }

    public void sayHello() {
        // hier wird geplappert
   }

    private void useName(String name) {
        this.name = name;
    }
}
