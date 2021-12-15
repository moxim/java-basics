package ch.finnova.java.schulung.people;

public class Programmer extends Person { // is-A
    private int linesOfCode;             // has-A

    public int getLinesOfCode() {
        return linesOfCode;
    }

    public void sayHello(String aName) {
        System.out.println(getName() + " ignores " + aName + " and keeps hacking.");
    }
}
