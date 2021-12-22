package ch.finnova.java.schulung.alinda;

public class Person {
    private static String name;
    private Object coolThing;

    public void setName(String newName) {
        name = newName; // referencing a static variable from within a non-static method is pretty darn dangerous
    }

    private String getName() {
        return name;
    }

    public void sayHello() {
        System.out.println(name + " says: Hello");
    }

    public void sayHelloTo(Person eve) {
        System.out.println(name + " says: Hello " + eve.getName());
    }

    public static void doSomethingCool() {
        coolThing = null; // this is impossible
    }

     public static void main(String[] args) {
        Person adam = new Person();
        adam.setName("Adam");

        Person eve = new Person();
        eve.setName("Eve");

        adam.sayHello();
        adam.sayHelloTo(eve);
    }
}
