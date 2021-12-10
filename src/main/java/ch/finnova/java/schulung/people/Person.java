package ch.finnova.java.schulung.people;

public class Person {
    private String name;

    public void doSomethingCool() {
        this.sayHello();
    }

    public void sayHello() {
        use(this.name); // hier wird geplappert
   }

    private void use(String aName) {
    }
}
